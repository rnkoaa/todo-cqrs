package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 6/26/17.
 */
@Profile("jpa")
public interface JpaDomainEventRepository extends JpaRepository<JpaDomainEvent, Long> {

    List<JpaDomainEvent> findByAggregateId(String aggregateId);
}