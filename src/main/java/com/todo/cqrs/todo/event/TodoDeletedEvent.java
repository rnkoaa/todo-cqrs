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
@JsonDeserialize(builder = TodoDeletedEvent.TodoDeletedEventBuilder.class)
public class TodoDeletedEvent extends DomainEvent{
    public TodoDeletedEvent(String todoId, int version, long timestamp) {
        super(todoId, version, timestamp, TodoEvent.DELETED_EVENT);
    }


    @JsonPOJOBuilder(withPrefix = "")
    public static final class TodoDeletedEventBuilder {
        private String aggregateId;
        private int version;
        private long timestamp;
        private String eventType;



        public TodoDeletedEventBuilder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public TodoDeletedEventBuilder version(int version) {
            this.version = version;
            return this;
        }


        public TodoDeletedEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TodoDeletedEventBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public TodoDeletedEvent build() {
            return new TodoDeletedEvent(aggregateId, version, timestamp);
        }
    }
}
