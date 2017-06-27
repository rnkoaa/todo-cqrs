package com.todo.cqrs.todo.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.todo.cqrs.lib.DomainEvent;
import lombok.Builder;

/**
 * Created by 6/22/17.
 */

@Builder
@JsonDeserialize(builder = TodoCompletedEvent.TodoCompletedEventBuilder.class)
public class TodoCompletedEvent extends DomainEvent {
    public TodoCompletedEvent(String todoId, int version, long timestamp) {
        super(todoId, version, timestamp, TodoEvent.COMPLETED_EVENT);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TodoCompletedEventBuilder {
        private String aggregateId;
        private int version;
        private long timestamp;
        private String eventType;


        public TodoCompletedEventBuilder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public TodoCompletedEventBuilder version(int version) {
            this.version = version;
            return this;
        }


        public TodoCompletedEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TodoCompletedEventBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public TodoDeletedEvent build() {
            return new TodoDeletedEvent(aggregateId, version, timestamp);
        }
    }
}
