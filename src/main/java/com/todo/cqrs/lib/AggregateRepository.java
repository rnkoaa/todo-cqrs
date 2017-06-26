package com.todo.cqrs.lib;

/**
 * Created by 6/21/17.
 */
public interface AggregateRepository<T extends AggregateRoot> {
    void save(T aggregateRoot);

    T load(String id, Class<T> aggregateType);

}
