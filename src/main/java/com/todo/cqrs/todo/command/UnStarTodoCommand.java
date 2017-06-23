package com.todo.cqrs.todo.command;

import com.todo.cqrs.lib.Command;
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
public class UnStarTodoCommand extends Command {
    private final TodoId todoId;
}

