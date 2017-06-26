package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;

import java.util.List;

/**
 * Created on 6/26/2017.
 */
public interface CustomDomainEventRepository {

    List<DomainEvent> findDomainEvents(String aggregateId);

    List<DomainEvent> findDomainEvents();
}
