package com.todo.cqrs;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.ValueId;

import java.util.List;

/**
 * Created by 6/24/17.
 */
public interface DomainEventService<T extends ValueId> {

    List<DomainEvent> findAll();

    List<DomainEvent> findByAggregate(String aggregateId);

    List<DomainEvent> findByAggregate(T aggregateId);
}
