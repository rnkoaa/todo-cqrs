package com.todo.cqrs.todo;

import com.todo.cqrs.lib.AggregateRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by 6/22/17.
 */
public interface TodoRepository extends AggregateRepository<TodoAggregate, TodoId> {
    //void save(TodoAggregate todoAggregate);

    Optional<TodoAggregate> find(TodoId todoId);

    Optional<TodoAggregate> find(String todoId);

    /**
     * Loads an aggregate from its history
     * @param todoId
     * @return
     */
    Optional<TodoAggregate> findFromHistory(TodoId todoId);

    /**
     * Loads an aggregate from its history
     * @param todoId
     * @return
     */
    Optional<TodoAggregate> findFromHistory(String todoId);

    Optional<TodoAggregate> findWithEvents(String todoId);

    List<TodoAggregate> allAggregates();


}
