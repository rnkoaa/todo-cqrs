package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.DomainEventService;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 6/26/17.
 */
@Service("domainEventService")
@Profile("jpa")
public class JpaDomainEventServiceImpl implements DomainEventService<TodoId> {

    private final JpaDomainEventRepository domainEventRepository;

    public JpaDomainEventServiceImpl(JpaDomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public List<DomainEvent> findAll() {

        return domainEventRepository.findAll()
                .stream()
                .map(JpaDomainEvent::getDomainEvent)
                .collect(Collectors.toList());
    }

    @Override
    public List<DomainEvent> findByAggregate(String aggregateId) {
        return null;
    }

    @Override
    public List<DomainEvent> findByAggregate(TodoId aggregateId) {
        return null;
    }
}
