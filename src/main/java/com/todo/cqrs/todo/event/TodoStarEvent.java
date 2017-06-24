package com.todo.cqrs.todo.event;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoId;

/**
 * Created by 6/22/17.
 */
public class TodoStarEvent extends DomainEvent<TodoId> {
    private final boolean starred;

    public TodoStarEvent(TodoId todoId, int version, long timestamp, boolean starred) {
        super(todoId, version, timestamp, TodoEvent.STARRED_EVENT);
        this.starred = starred;
    }

    public boolean isStarred() {
        return starred;
    }
}
