package com.todo.cqrs.todo.command;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.todo.cqrs.lib.CommandHandler;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by 6/22/17.
 */
@Component("todoCommandHandler")
public class TodoCommandHandler implements CommandHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(TodoCommandHandler.class);

    private final TodoRepository todoRepository;

    public TodoCommandHandler(EventBus commandEventBus,
                              TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        commandEventBus.register(this);
    }


    @Subscribe
    public void handle(CreateTodoCommand command) {
        LOGGER.info("Creating a new todo with a command.");

        TodoAggregate todoAggregate = new TodoAggregate(command);

        //save the aggregate
        todoRepository.save(todoAggregate);
    }

    @Subscribe
    public void handle(UpdateTodoDescriptionCommand command) {
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());
        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.updateDescription(command);
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(CompleteTodoCommand command) {
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());
        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.completeTodo(command);
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(DeleteTodoCommand command) {
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());
        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.deleteTodo(command);
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(StarTodoCommand command) {
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());
        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.starTodo(command);
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(UnStarTodoCommand command) {
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());
        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.unStarTodo(command);
            todoRepository.save(existingTodoAggregate);
        });
    }
}
