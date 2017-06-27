package com.todo.cqrs.todo.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.todo.cqrs.lib.DomainEvent;
import lombok.Builder;

/**
 * Created by 6/22/17.
 */

@Builder
@JsonDeserialize(builder = TodoDescriptionUpdatedEvent.TodoDescriptionUpdatedEventBuilder.class)
public class TodoDescriptionUpdatedEvent extends DomainEvent {
    private final String description;

    public TodoDescriptionUpdatedEvent(String todoId, int version, String description, long timestamp) {
        super(todoId, version, timestamp, TodoEvent.DESCRIPTION_UPDATED_EVENT);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TodoDescriptionUpdatedEventBuilder {
        private String aggregateId;
        private int version;
        private long timestamp;
        private String eventType;
        private String description;

        public TodoDescriptionUpdatedEventBuilder description(String description) {
            this.description = description;
            return this;
        }


        public TodoDescriptionUpdatedEventBuilder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public TodoDescriptionUpdatedEventBuilder version(int version) {
            this.version = version;
            return this;
        }


        public TodoDescriptionUpdatedEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TodoDescriptionUpdatedEventBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public TodoDescriptionUpdatedEvent build() {
            return new TodoDescriptionUpdatedEvent(aggregateId, version, description, timestamp);
        }

    }
}
