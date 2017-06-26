package com.todo.cqrs.todo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.todo.cqrs.lib.ValueId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Tolerate;

import java.util.UUID;

/**
 * Created by 6/21/17.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString
@Builder
@JsonDeserialize(builder = TodoId.TodoIdBuilder.class)
public class TodoId extends ValueId {
    @Tolerate
    public TodoId() {
        super(UUID.randomUUID().toString());
    }

    public TodoId(String id) {
        super(id);
    }

    public static TodoId randomId() {
        return new TodoId(UUID.randomUUID().toString());
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class TodoIdBuilder {

    }
}
