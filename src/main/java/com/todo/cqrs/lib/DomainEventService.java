package com.todo.cqrs.lib;

import java.util.List;

/**
 * Created by 6/24/17.
 */
public interface DomainEventService {

    List<? extends DomainEvent> findAll();

    DomainEvent save(DomainEvent domainEvent);

    List<? extends DomainEvent> save(List<? extends DomainEvent> domainEvents);

    List<? extends DomainEvent> findByAggregate(String aggregateId);

}
