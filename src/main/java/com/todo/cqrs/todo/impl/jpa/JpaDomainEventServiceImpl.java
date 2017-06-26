package com.todo.cqrs.todo.impl.jpa;

import com.google.common.base.Strings;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 6/26/17.
 */
@Service("domainEventService")
@Profile("jpa")
public class JpaDomainEventServiceImpl implements DomainEventService {

    private final JpaDomainEventRepository domainEventRepository;
    private final CustomDomainEventRepository customDomainEventRepository;

    public JpaDomainEventServiceImpl(JpaDomainEventRepository domainEventRepository,
                                     CustomDomainEventRepository customDomainEventRepository) {
        this.domainEventRepository = domainEventRepository;
        this.customDomainEventRepository = customDomainEventRepository;
    }

    @Override
    public List<DomainEvent> findAll() {

      /*  return domainEventRepository.findAll()
                .stream()
                .map(JpaDomainEvent::getDomainEvent)
                .collect(Collectors.toList());*/
        return customDomainEventRepository.findDomainEvents();
    }

 /*   @Override
    public List<DomainEvent> findByAggregate(String aggregateId) {
        return domainEventRepository
                .findByAggregateId(aggregateId)
                .stream()
                .map(JpaDomainEvent::getDomainEvent)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<DomainEvent> findByAggregate(String todoId) {
        Objects.requireNonNull(todoId, "Id for Aggregate root should not be null.");
        if (Strings.isNullOrEmpty(todoId))
            throw new IllegalArgumentException("Id for Aggregate root should not be null.");

        return domainEventRepository
                .findByAggregateId(todoId)
                .stream()
                .map(JpaDomainEvent::getDomainEvent)
                .collect(Collectors.toList());
    }
}
