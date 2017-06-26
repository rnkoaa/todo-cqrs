package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoId;
import com.todo.cqrs.todo.TodoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 6/26/17.
 */
@Service("todoRepository")
@Profile("jpa")
public class JpaTodoRepositoryImpl implements TodoRepository {
    @Override
    public void save(TodoAggregate aggregateRoot) {

    }

    @Override
    public TodoAggregate load(TodoId id, Class<TodoAggregate> aggregateType) {
        return null;
    }

    @Override
    public Optional<TodoAggregate> find(TodoId todoId) {
        return null;
    }

    @Override
    public Optional<TodoAggregate> find(String todoId) {
        return null;
    }

    @Override
    public Optional<TodoAggregate> findFromHistory(TodoId todoId) {
        return null;
    }

    @Override
    public Optional<TodoAggregate> findFromHistory(String todoId) {
        return null;
    }

    @Override
    public Optional<TodoAggregate> findWithEvents(String todoId) {
        return null;
    }

    @Override
    public List<TodoAggregate> allAggregates() {
        return null;
    }
}
