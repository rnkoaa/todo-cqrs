package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;

/**
 * Created by 6/22/17.
 */
public class TodoDeletedEvent extends DomainEvent<TodoId> {
    public TodoDeletedEvent(TodoId todoId, int version, long timestamp) {
        super(todoId, version, timestamp);

    }
}
