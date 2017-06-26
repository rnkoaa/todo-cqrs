package com.todo.cqrs.todo.command.handler;

import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.CommandHandler;
import com.todo.cqrs.lib.command.ResultValue;
import com.todo.cqrs.todo.InvalidTodoStateException;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.command.UnStarTodoCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by 6/22/17.
 */
@Component("unStarTodoCommandHandler")
public class UnStarTodoCommandHandler implements CommandHandler<UnStarTodoCommand, ResultValue> {

    private final Logger LOGGER = LoggerFactory.getLogger(UnStarTodoCommandHandler.class);

    private final TodoRepository todoRepository;

    public UnStarTodoCommandHandler(CommandBus commandBus,
                                    TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandBus.register(UnStarTodoCommand.class, this);
    }

    @Override
    public ResultValue handle(UnStarTodoCommand command) {
        LOGGER.info("unfavoriting todo description with a id: {}", command.getTodoId());
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot remove favorite of a Todo which is deleted");
            existingTodoAggregate.unStarTodo(command);
            existingTodoAggregate.setDeleted(false);
            todoRepository.save(existingTodoAggregate);
        });
        return new ResultValue();
    }
}
