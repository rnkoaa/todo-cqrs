package com.todo.cqrs.todo.command.handler;

import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.CommandHandler;
import com.todo.cqrs.lib.command.ResultValue;
import com.todo.cqrs.todo.InvalidTodoStateException;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.command.CompleteTodoCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by 6/22/17.
 */
@Component("completeTodoCommandHandler")
public class CompleteTodoCommandHandler implements CommandHandler<CompleteTodoCommand, ResultValue> {

    private final Logger LOGGER = LoggerFactory.getLogger(CompleteTodoCommandHandler.class);

    private final TodoRepository todoRepository;

    public CompleteTodoCommandHandler(CommandBus commandBus,
                                      TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandBus.register(CompleteTodoCommand.class, this);
    }

    @Override
    public ResultValue handle(CompleteTodoCommand command) {
        LOGGER.info("completing todo with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot complete Todo which is deleted");
            existingTodoAggregate.completeTodo(command);
            existingTodoAggregate.setCompletedOn(LocalDateTime.now());
            todoRepository.save(existingTodoAggregate);
        });
        return new ResultValue();
    }
}
