package com.todo.cqrs.todo.event;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoId;

/**
 * Created by 6/22/17.
 */
public class TodoCompletedEvent extends DomainEvent {
    public TodoCompletedEvent(String todoId, int version, long timestamp) {
        super(todoId, version, timestamp, TodoEvent.COMPLETED_EVENT);

    }
}
