package com.todo.cqrs.todo.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by 6/23/17.
 */
public enum TodoEvent {
    COMPLETED_EVENT("TodoCompletedEvent"),
    DELETED_EVENT("TodoDeletedEvent"),
    STARRED_EVENT("TodoStarEvent"),
    DESCRIPTION_UPDATED_EVENT("TodoDescriptionUpdatedEvent"),
    CREATED_EVENT("TodoCreatedEvent");

    private final String eventName;

    TodoEvent(String eventName) {
        this.eventName = eventName;
    }

    @JsonCreator
    public static TodoEvent forValue(String value) {
        return TodoEvent.valueOf(value);
    }

    @JsonValue
    public String getName() {
        return eventName;
    }

    @Override
    public String toString() {
        return eventName;
    }
}
