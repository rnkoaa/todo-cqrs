package com.todo.cqrs.todo.command;

import com.todo.cqrs.lib.command.Command;
import com.todo.cqrs.todo.TodoId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created by 6/22/17.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@Builder
public class StarTodoCommand extends Command {
    private final String todoId;
}

