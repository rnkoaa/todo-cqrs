package com.todo.cqrs.todo.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoId;
import lombok.Builder;

/**
 * Created by 6/22/17.
 */

@Builder
@JsonDeserialize(builder = TodoStarEvent.TodoStarEventBuilder.class)
public class TodoStarEvent extends DomainEvent {
    private final boolean starred;

    public TodoStarEvent(String todoId, int version, long timestamp, boolean starred) {
        super(todoId, version, timestamp, TodoEvent.STARRED_EVENT);
        this.starred = starred;
    }

    public boolean isStarred() {
        return starred;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TodoStarEventBuilder {
        private String aggregateId;
        private int version;
        private long timestamp;
        private String eventType;
        private boolean starred;

        public TodoStarEventBuilder starred(boolean starred) {
            this.starred = starred;
            return this;
        }


        public TodoStarEventBuilder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public TodoStarEventBuilder version(int version) {
            this.version = version;
            return this;
        }


        public TodoStarEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TodoStarEventBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public TodoStarEvent build() {
            return new TodoStarEvent(aggregateId, version, timestamp, starred);
        }
    }
}
