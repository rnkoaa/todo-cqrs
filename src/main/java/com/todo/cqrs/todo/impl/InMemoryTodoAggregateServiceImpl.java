package com.todo.cqrs.todo.impl;

import com.todo.cqrs.lib.AggregateRoot;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.NotFoundException;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoAggregateService;
import com.todo.cqrs.todo.TodoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created on 6/23/2017.
 */
@Service("todoAggregateService")
@Profile("in-memory")
public class InMemoryTodoAggregateServiceImpl implements TodoAggregateService {

    private final TodoRepository todoRepository;

    public InMemoryTodoAggregateServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoAggregate> findAll() {
        return todoRepository.allAggregates();
    }

    @Override
    public Optional<TodoAggregate> find(String aggregateId) {
        return todoRepository.find(aggregateId);
    }

    @Override
    public List<? extends DomainEvent> getEvents(String aggregateId) {
        return todoRepository.findWithEvents(aggregateId)
                .map(AggregateRoot::getUncommittedEvents)
                .orElseThrow(() -> new NotFoundException("No Events found for TodoAggregate with Id: " + aggregateId + "Does not exist"));
    }
}
