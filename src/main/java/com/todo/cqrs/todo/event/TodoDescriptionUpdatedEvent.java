package com.todo.cqrs.todo.event;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoId;

/**
 * Created by 6/22/17.
 */
public class TodoDescriptionUpdatedEvent extends DomainEvent {
    private final String description;

    public TodoDescriptionUpdatedEvent(String todoId, int version, String description, long timestamp) {
        super(todoId, version, timestamp, TodoEvent.DESCRIPTION_UPDATED_EVENT);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
