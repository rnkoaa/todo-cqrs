package com.todo.cqrs.todo.command;

import com.todo.cqrs.lib.command.Command;
import com.todo.cqrs.todo.TodoId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created by 6/22/17.
 */
@EqualsAndHashCode(callSuper = false)
@Value
@Builder
public class UpdateTodoDescriptionCommand extends Command {
    private final String todoId;
    private final String description;
}
