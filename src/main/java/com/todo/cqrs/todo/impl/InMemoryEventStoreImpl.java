package com.todo.cqrs.todo.impl;

import com.google.common.collect.*;
import com.todo.cqrs.lib.AggregateRoot;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventStore;
import com.todo.cqrs.lib.ValueId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by 6/23/17.
 */
@Component("domainEventStore")
@Profile("in-memory")
public class InMemoryEventStoreImpl implements DomainEventStore {
    private Multimap<ValueId, DomainEvent> domainEventsMap = LinkedHashMultimap.create();

    @Override
    public List<DomainEvent> loadEvents(ValueId id) {
        Collection<DomainEvent> domainEvents = domainEventsMap.get(id);
        if (domainEvents.isEmpty())
            throw new IllegalArgumentException("Aggregate does not exist");
        return new ArrayList<>(domainEvents);
    }

    @Override
    public synchronized void save(ValueId id, Class<? extends AggregateRoot> type, List<DomainEvent> events) {
        domainEventsMap.putAll(id, events);
    }

    @Override
    public List<DomainEvent> getAllEvents() {
        Collection<DomainEvent> events = domainEventsMap.values();
        List<DomainEvent> domainEvents = Lists.newArrayList(events);
        Collections.reverse(domainEvents);
        return domainEvents;
    }
}
