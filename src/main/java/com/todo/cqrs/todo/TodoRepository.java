package com.todo.cqrs.todo;

import java.util.List;
import java.util.Optional;

/**
 * Created by 6/22/17.
 */
public interface TodoRepository {
    void save(TodoAggregate todoAggregate);

    Optional<TodoAggregate> find(TodoId todoId);

    Optional<TodoAggregate> find(String todoId);

    Optional<TodoAggregate> findWithEvents(String todoId);

    List<TodoAggregate> allAggregates();
}
