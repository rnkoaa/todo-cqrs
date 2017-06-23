package com.todo.cqrs.todo;

import com.todo.cqrs.lib.ValueId;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.UUID;

/**
 * Created by 6/21/17.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString
public class TodoId extends ValueId {
    public TodoId(String id) {
        super(id);
    }

    public static TodoId randomId() {
        return new TodoId(UUID.randomUUID().toString());
    }
}
