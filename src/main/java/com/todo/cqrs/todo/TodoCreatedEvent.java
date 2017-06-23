package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;

import java.time.LocalDateTime;

/**
 * Created by 6/21/17.
 */
public class TodoCreatedEvent extends DomainEvent<TodoId> {
    private final String description;

    public TodoCreatedEvent(TodoId todoId, int nextVersion, String description, long timestamp) {
        super(todoId, nextVersion, timestamp);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
