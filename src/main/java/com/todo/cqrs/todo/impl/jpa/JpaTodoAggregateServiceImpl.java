package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoAggregateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 6/26/17.
 */
@Service("todoAggregateService")
@Profile("jpa")
public class JpaTodoAggregateServiceImpl implements TodoAggregateService {
    @Override
    public List<TodoAggregate> findAll() {
        return null;
    }

    @Override
    public Optional<TodoAggregate> find(String aggregateId) {
        return null;
    }

    @Override
    public List<? extends DomainEvent> getEvents(String aggregateId) {
        return null;
    }
}
