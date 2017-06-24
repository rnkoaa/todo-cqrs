package com.todo.cqrs.todo.command.handler;

import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.CommandHandler;
import com.todo.cqrs.lib.command.ResultValue;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.command.DeleteTodoCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by 6/22/17.
 */
@Component("deleteTodoCommandHandler")
public class DeleteTodoCommandHandler implements CommandHandler<DeleteTodoCommand, ResultValue> {

    private final Logger LOGGER = LoggerFactory.getLogger(DeleteTodoCommandHandler.class);

    private final TodoRepository todoRepository;

    public DeleteTodoCommandHandler(CommandBus commandBus,
                                    TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandBus.register(DeleteTodoCommand.class, this);
    }

    @Override
    public ResultValue handle(DeleteTodoCommand command) {
        LOGGER.info("deleting todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.deleteTodo(command);
            existingTodoAggregate.setDeleted(true);
            todoRepository.save(existingTodoAggregate);
        });
        return new ResultValue();
    }
}
