package com.todo.cqrs.todo.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.impl.jpa.RawEvent;

import java.io.IOException;

/**
 * Created by 6/23/17.
 */
public enum TodoEvent {
    COMPLETED_EVENT("TodoCompletedEvent") {
        public RawEvent getEvent(DomainEvent domainEvent, ObjectMapper objectMapper) throws JsonProcessingException {
            TodoCompletedEvent completedEvent = (TodoCompletedEvent) domainEvent;
            return new RawEvent(completedEvent.getAggregateId(), name(), objectMapper.writeValueAsString(completedEvent));
        }

        @Override
        public TodoCompletedEvent map(RawEvent rawEvent, ObjectMapper objectMapper) throws IOException {
            String event = rawEvent.getPayload();
            if (!Strings.isNullOrEmpty(event))
                return objectMapper.readValue(event, TodoCompletedEvent.class);
            return null;
        }
    },
    DELETED_EVENT("TodoDeletedEvent") {
        @Override
        public RawEvent getEvent(DomainEvent domainEvent, ObjectMapper objectMapper) throws JsonProcessingException {
            TodoDeletedEvent deletedEvent = (TodoDeletedEvent) domainEvent;
            return new RawEvent(deletedEvent.getAggregateId(), name(), objectMapper.writeValueAsString(deletedEvent));
        }

        @Override
        public TodoDeletedEvent map(RawEvent rawEvent, ObjectMapper objectMapper) throws IOException {
            String event = rawEvent.getPayload();
            if (!Strings.isNullOrEmpty(event))
                return objectMapper.readValue(event, TodoDeletedEvent.class);
            return null;
        }
    },
    STARRED_EVENT("TodoStarEvent") {
        @Override
        public RawEvent getEvent(DomainEvent domainEvent, ObjectMapper objectMapper) throws JsonProcessingException {
            TodoStarEvent starEvent = (TodoStarEvent) domainEvent;
            return new RawEvent(starEvent.getAggregateId(), name(), objectMapper.writeValueAsString(starEvent));
        }

        @Override
        public TodoStarEvent map(RawEvent rawEvent, ObjectMapper objectMapper) throws IOException {
            String event = rawEvent.getPayload();
            if (!Strings.isNullOrEmpty(event))
                return objectMapper.readValue(event, TodoStarEvent.class);
            return null;
        }
    },
    DESCRIPTION_UPDATED_EVENT("TodoDescriptionUpdatedEvent") {
        @Override
        public RawEvent getEvent(DomainEvent domainEvent, ObjectMapper objectMapper) throws JsonProcessingException {
            TodoDescriptionUpdatedEvent descriptionUpdatedEvent = (TodoDescriptionUpdatedEvent) domainEvent;
            return new RawEvent(descriptionUpdatedEvent.getAggregateId(), name(), objectMapper.writeValueAsString(descriptionUpdatedEvent));
        }

        @Override
        public TodoDescriptionUpdatedEvent map(RawEvent rawEvent, ObjectMapper objectMapper) throws IOException {
            String event = rawEvent.getPayload();
            if (!Strings.isNullOrEmpty(event))
                return objectMapper.readValue(event, TodoDescriptionUpdatedEvent.class);
            return null;
        }
    },
    CREATED_EVENT("TodoCreatedEvent") {
        @Override
        public RawEvent getEvent(DomainEvent domainEvent, ObjectMapper objectMapper) throws JsonProcessingException {
            TodoCreatedEvent createdEvent = (TodoCreatedEvent) domainEvent;
            return new RawEvent(createdEvent.getAggregateId(), getName(), objectMapper.writeValueAsString(createdEvent));
        }

        @Override
        public TodoCreatedEvent map(RawEvent rawEvent, ObjectMapper objectMapper) throws IOException {
            String event = rawEvent.getPayload();
            if (!Strings.isNullOrEmpty(event))
                return objectMapper.readValue(event, TodoCreatedEvent.class);
            return null;
        }
    };

    private final String eventName;

    TodoEvent(String eventName) {
        this.eventName = eventName;
    }

    @JsonCreator
    public static TodoEvent forValue(String value) {
        return TodoEvent.valueOf(value);
    }

    @JsonValue
    public String getName() {
        return eventName;
    }

    @Override
    public String toString() {
        return eventName;
    }

    public abstract RawEvent getEvent(DomainEvent domainEvent, ObjectMapper objectMapper) throws JsonProcessingException;

    public abstract DomainEvent map(RawEvent rawEvent, ObjectMapper objectMapper) throws IOException;
}
