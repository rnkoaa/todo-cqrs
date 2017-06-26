package com.todo.cqrs.todo.impl.inmemory;

import com.todo.cqrs.lib.DomainEventService;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventStore;
import com.todo.cqrs.todo.TodoId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 6/24/17.
 */
@Service("domainEventService")
@Profile("in-memory")
public class InMemoryDomainEventService implements DomainEventService {

    private final DomainEventStore domainEventStore;

    public InMemoryDomainEventService(DomainEventStore domainEventStore) {
        this.domainEventStore = domainEventStore;
    }

    @Override
    public List<DomainEvent> findAll() {
        return domainEventStore.getAllEvents();
    }

   /* @Override
    public List<DomainEvent> findByAggregate(String aggregateId) {
        TodoId todoId = new TodoId(aggregateId);
        return findByAggregate(todoId);
    }*/

    @Override
    public List<DomainEvent> findByAggregate(String aggregateId) {
        return domainEventStore.loadEvents(aggregateId);
    }
}
