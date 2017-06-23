package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;

/**
 * Created by 6/22/17.
 */
public class TodoStarEvent extends DomainEvent<TodoId> {
    private final boolean starred;

    public TodoStarEvent(TodoId todoId, int version, long timestamp, boolean starred) {
        super(todoId, version, timestamp);
        this.starred = starred;
    }

    public boolean isStarred() {
        return starred;
    }
}
