package com.todo.cqrs.lib;

import java.util.List;

/**
 * Created by 6/24/17.
 */
public interface DomainEventService {

    List<DomainEvent> findAll();

    List<DomainEvent> findByAggregate(String aggregateId);

    //List<DomainEvent> findByAggregate(String aggregateId);
}
