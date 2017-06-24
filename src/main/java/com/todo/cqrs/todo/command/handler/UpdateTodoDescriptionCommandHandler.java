package com.todo.cqrs.todo.command.handler;

import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.CommandHandler;
import com.todo.cqrs.lib.command.ResultValue;
import com.todo.cqrs.todo.InvalidTodoStateException;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.command.UpdateTodoDescriptionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by 6/22/17.
 */
@Component("updateTodoDescriptionCommandHandler")
public class UpdateTodoDescriptionCommandHandler implements CommandHandler<UpdateTodoDescriptionCommand, ResultValue> {

    private final Logger LOGGER = LoggerFactory.getLogger(UpdateTodoDescriptionCommandHandler.class);

    private final TodoRepository todoRepository;

    public UpdateTodoDescriptionCommandHandler(CommandBus commandBus,
                                               TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandBus.register(UpdateTodoDescriptionCommand.class, this);
    }

    @Override
    public ResultValue handle(UpdateTodoDescriptionCommand command) {
        LOGGER.info("updating todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot update Todo which is deleted");
            existingTodoAggregate.updateDescription(command);
            existingTodoAggregate.setDescription(command.getDescription());
            todoRepository.save(existingTodoAggregate);
        });
        return new ResultValue();
    }
}
