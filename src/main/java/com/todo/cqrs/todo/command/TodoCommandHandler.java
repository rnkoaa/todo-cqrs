package com.todo.cqrs.todo.command;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.todo.cqrs.lib.CommandHandler;
import com.todo.cqrs.todo.InvalidTodoStateException;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        LOGGER.info("Creating a new todo with a id: {}", command.getTodoId().id);

        TodoAggregate todoAggregate = new TodoAggregate(command);

        //save the aggregate
        todoRepository.save(todoAggregate);
    }

    @Subscribe
    public void handle(UpdateTodoDescriptionCommand command) {
        LOGGER.info("updating todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot update Todo which is deleted");
            existingTodoAggregate.updateDescription(command);
            existingTodoAggregate.setDescription(command.getDescription());
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(CompleteTodoCommand command) {
        LOGGER.info("completing todo with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot complete Todo which is deleted");
            existingTodoAggregate.completeTodo(command);
            existingTodoAggregate.setCompletedOn(LocalDateTime.now());
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(DeleteTodoCommand command) {
        LOGGER.info("deleting todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            existingTodoAggregate.deleteTodo(command);
            existingTodoAggregate.setDeleted(true);
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(StarTodoCommand command) {
        LOGGER.info("favoriting todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot favorite Todo which is deleted");

            existingTodoAggregate.starTodo(command);
            existingTodoAggregate.setStarred(true);
            todoRepository.save(existingTodoAggregate);
        });
    }

    @Subscribe
    public void handle(UnStarTodoCommand command) {
        LOGGER.info("unfavoriting todo description with a id: {}", command.getTodoId().id);
        Optional<TodoAggregate> todoAggregate = todoRepository.find(command.getTodoId());

        todoAggregate.ifPresent(existingTodoAggregate -> {
            if (existingTodoAggregate.isDeleted())
                throw new InvalidTodoStateException("Cannot remove favorite of a Todo which is deleted");
            existingTodoAggregate.unStarTodo(command);
            existingTodoAggregate.setDeleted(false);
            todoRepository.save(existingTodoAggregate);
        });
    }
}
