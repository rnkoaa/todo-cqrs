package com.todo.cqrs.todo;

import com.todo.cqrs.lib.AggregateRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by 6/22/17.
 */
public interface TodoRepository extends AggregateRepository<TodoAggregate> {
    //void save(TodoAggregate todoAggregate);

 /*   Optional<TodoAggregate> find(String todoId);*/

    Optional<TodoAggregate> find(String todoId);

    /**
     * Loads an aggregate from its history
     * @param todoId
     * @return
     */
  /*  Optional<TodoAggregate> findFromHistory(String todoId);*/

    /**
     * Loads an aggregate from its history
     * @param todoId
     * @return
     */
    Optional<TodoAggregate> findFromHistory(String todoId);

    Optional<TodoAggregate> findWithEvents(String todoId);

    List<TodoAggregate> allAggregates();


}
