package com.todo.cqrs.todo.command.handler;

import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.CommandHandler;
import com.todo.cqrs.lib.command.ResultValue;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.command.CreateTodoCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 6/23/17.
 */
@Component("createTodoCommandHandler")
public class CreateTodoCommandHandler implements CommandHandler<CreateTodoCommand, ResultValue> {

    private final Logger LOGGER = LoggerFactory.getLogger(CreateTodoCommandHandler.class);

    private final TodoRepository todoRepository;

    public CreateTodoCommandHandler(CommandBus commandBus,
                                    TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandBus.register(CreateTodoCommand.class, this);
    }

    @Override
    public ResultValue handle(CreateTodoCommand command) {
        LOGGER.info("Creating a new todo with a id: {}", command.getTodoId());

        TodoAggregate todoAggregate = new TodoAggregate(command);

        //save the aggregate
        todoRepository.save(todoAggregate);
        return new ResultValue();
    }
}
