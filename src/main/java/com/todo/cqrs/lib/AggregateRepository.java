package com.todo.cqrs.lib;

/**
 * Created by 6/21/17.
 */
public interface AggregateRepository {
    <T extends AggregateRoot> void save(T aggregateRoot);

    <T extends AggregateRoot, ID extends ValueId> T load(ID id, Class<T> aggregateType);

}
