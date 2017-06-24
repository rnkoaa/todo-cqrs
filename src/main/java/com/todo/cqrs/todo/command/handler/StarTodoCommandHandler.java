package com.todo.cqrs.todo.command.handler;

import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.CommandHandler;
import com.todo.cqrs.lib.command.ResultValue;
import com.todo.cqrs.todo.InvalidTodoStateException;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.command.StarTodoCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by 6/22/17.
 */
@Component("starTodoCommandHandler")
public class StarTodoCommandHandler implements CommandHandler<StarTodoCommand, ResultValue> {

    private final Logger LOGGER = LoggerFactory.getLogger(StarTodoCommandHandler.class);

    private final TodoRepository todoRepository;

    public StarTodoCommandHandler(CommandBus commandBus,
                                  TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandBus.register(StarTodoCommand.class, this);
    }

    @Override
    public ResultValue handle(StarTodoCommand command) {
        LOGGER.info("favoriting todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot favorite Todo which is deleted");

            existingTodoAggregate.starTodo(command);
            existingTodoAggregate.setStarred(true);
            todoRepository.save(existingTodoAggregate);
        });
        return new ResultValue();
    }
}
