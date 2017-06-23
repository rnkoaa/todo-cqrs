package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;

/**
 * Created by 6/22/17.
 */
public class TodoDescriptionUpdatedEvent extends DomainEvent<TodoId> {
    private final String description;

    public TodoDescriptionUpdatedEvent(TodoId todoId, int version, String description, long timestamp) {
        super(todoId, version, timestamp);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
