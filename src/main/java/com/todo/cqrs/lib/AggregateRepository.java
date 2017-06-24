package com.todo.cqrs.lib;

/**
 * Created by 6/21/17.
 */
public interface AggregateRepository<T extends AggregateRoot, ID extends ValueId> {
    void save(T aggregateRoot);

    T load(ID id, Class<T> aggregateType);

}
