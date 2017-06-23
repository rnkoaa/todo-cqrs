package com.todo.cqrs.todo.command;

import com.todo.cqrs.lib.Command;
import com.todo.cqrs.todo.TodoId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Created by 6/22/17.
 */
@EqualsAndHashCode(callSuper = true)
@Value
@Builder
public class CreateTodoCommand extends Command {
    private final TodoId todoId;
    private final String description;
}
