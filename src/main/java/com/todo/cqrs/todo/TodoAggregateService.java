package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;

import java.util.List;
import java.util.Optional;

/**
 * Created on 6/23/2017.
 */
public interface TodoAggregateService {
    List<TodoAggregate> findAll();

    Optional<TodoAggregate> find(String aggregateId);

    List<? extends DomainEvent> getEvents(String aggregateId);
}
