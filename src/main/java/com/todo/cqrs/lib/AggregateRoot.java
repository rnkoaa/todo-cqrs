package com.todo.cqrs.lib;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/21/2017.
 */
public class AggregateRoot<T extends DomainEvent> {
    private final List<T> uncommittedEvents = new ArrayList<>();

    private String id;
    private int version = 0;
    private long timestamp = 0;

    public List<T> getUncommittedEvents() {
        return uncommittedEvents;
    }

    public void loadFromHistory(List<T> history) {
        for (T event : history) {
            applyChange(event, false);
        }
    }

    protected int nextVersion() {
        return version + 1;
    }

    protected long now() {
        return System.currentTimeMillis();
    }

    public String id() {
        return id;
    }

    public int version() {
        return version;
    }

    public long timestamp() {
        return timestamp;
    }

    protected void applyChange(T event) {
        applyChange(event, true);
    }

    private void applyChange(T event, boolean isNew) {
        updateMetadata(event);
        invokeHandlerMethod(event);
        if (isNew) uncommittedEvents.add(event);
    }

    @SuppressWarnings("unchecked")
    private void updateMetadata(T event) {
        this.id = event.getAggregateId();
        this.version = event.getVersion();
        this.timestamp = event.getTimestamp();
    }

    private void invokeHandlerMethod(T event) {
        Method handlerMethod = getHandlerMethod(event);
        if (handlerMethod != null) {
            handlerMethod.setAccessible(true);
            try {
                handlerMethod.invoke(this, event);
            } catch (Exception e) {
                throw new RuntimeException("Unable to call event handler method for " + event.getClass().getName(), e);
            }
        }
    }

    private Method getHandlerMethod(T event) {
        try {
            return getClass().getDeclaredMethod("handleEvent", event.getClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public boolean hasUncommittedEvents() {
        return !uncommittedEvents.isEmpty();
    }

    public void commitEvents() {
        getUncommittedEvents().clear();
    }
}
