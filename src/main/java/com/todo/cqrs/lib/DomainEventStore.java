package com.todo.cqrs.lib;

import java.util.List;

/**
 * Created by 6/21/17.
 */
public interface DomainEventStore {
    List<DomainEvent> loadEvents(String id);

    void save(String id, Class<? extends AggregateRoot> type, List<DomainEvent> events);

    List<DomainEvent> getAllEvents();
}
