package com.todo.cqrs.todo.impl.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventService;
import com.todo.cqrs.todo.event.TodoEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 6/26/17.
 */
@Service("domainEventService")
@Profile("jpa")
public class JpaDomainEventServiceImpl implements DomainEventService {


    private final ObjectMapper objectMapper;
    private final RawEventRepository rawEventRepository;

    public JpaDomainEventServiceImpl(ObjectMapper objectMapper,
                                     RawEventRepository rawEventRepository) {
        this.rawEventRepository = rawEventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<? extends DomainEvent> findAll() {

        List<RawEvent> rawEvents = rawEventRepository.findAll();
        return mapEvents(rawEvents);
    }

    @Override
    public DomainEvent save(DomainEvent domainEvent) {
        if (domainEvent.getEventType() != null) {
            try {
                RawEvent rawEvent = domainEvent.getEventType().getEvent(domainEvent, objectMapper);
                rawEventRepository.save(rawEvent);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return domainEvent;
    }

    private void updateBuilder(RawEvent.RawEventBuilder builder, DomainEvent domainEvent) throws JsonProcessingException {
        builder.aggregateId(domainEvent.getAggregateId())
                .eventType(domainEvent.getEventType())
                .payload(objectMapper.writeValueAsString(domainEvent));
    }

    @Override
    public List<? extends DomainEvent> save(List<? extends DomainEvent> domainEvents) {

        List<RawEvent> rawEvents = domainEvents.stream()
                .map(domainEvent -> {
                    if (domainEvent.getEventType() != null) {
                        try {
                            return domainEvent.getEventType().getEvent(domainEvent, objectMapper);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                })
                .collect(Collectors.toList());
        rawEventRepository.save(rawEvents);
        return domainEvents;
    }


    @Override
    public List<? extends DomainEvent> findByAggregate(String aggregateId) {
        Objects.requireNonNull(aggregateId, "Id for Aggregate root should not be null.");
        if (Strings.isNullOrEmpty(aggregateId))
            throw new IllegalArgumentException("Id for Aggregate root should not be null.");

        List<RawEvent> rawEvents = rawEventRepository.findByAggregateId(aggregateId);
        return mapEvents(rawEvents);
    }

    private List<? extends DomainEvent> mapEvents(List<RawEvent> rawEvents) {
        return rawEvents.stream()
                .map(rawEvent -> {
                    TodoEvent todoEvent = rawEvent.getEventType();
                    try {
                        return todoEvent.map(rawEvent, objectMapper);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
