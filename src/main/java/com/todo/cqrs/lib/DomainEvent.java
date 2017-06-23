package com.todo.cqrs.lib;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by 6/21/17.
 */
public abstract class DomainEvent<T extends ValueId> implements Serializable {

    private final T aggregateId;
    private final int version;
    private final long timestamp;

    protected DomainEvent(T aggregateId, int version, long timestamp) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainEvent)) return false;
        DomainEvent<?> that = (DomainEvent<?>) o;
        return version == that.version &&
                timestamp == that.timestamp &&
                Objects.equals(aggregateId, that.aggregateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId, version, timestamp);
    }

    public T getAggregateId() {
        return aggregateId;
    }

    public int getVersion() {
        return version;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
