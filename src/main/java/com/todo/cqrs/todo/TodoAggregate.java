package com.todo.cqrs.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.cqrs.lib.AggregateRoot;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.ValueId;
import com.todo.cqrs.todo.command.*;
import com.todo.cqrs.todo.event.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created on 6/21/2017.
 */
@Data
@Builder
@AllArgsConstructor
public class TodoAggregate extends AggregateRoot<DomainEvent> {

    @JsonIgnore
    private String id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedOn;
    private boolean deleted;
    private boolean starred;

    //for JPA
    @Tolerate
    public TodoAggregate() {
    }

    public TodoAggregate(CreateTodoCommand command) {
        Objects.requireNonNull(command, "command cannot be null");
        applyChange(new TodoCreatedEvent(command.getTodoId(), nextVersion(), command.getDescription(), now()));
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    void handleEvent(TodoCreatedEvent event) {
        this.id = event.getAggregateId();
        this.description = event.getDescription();
    }

    void handleEvent(TodoCompletedEvent event) {
        if (isDeleted())
            throw new InvalidTodoStateException("Cannot complete a deleted Todo.");
        this.completedOn = LocalDateTime.now();
    }

    void handleEvent(UpdateTodoDescriptionCommand event) {
        if (isDeleted())
            throw new InvalidTodoStateException("Cannot update the description of a deleted Todo.");
        this.description = event.getDescription();
    }


    void handleEvent(TodoDeletedEvent event) {
        this.deleted = true;
    }

    void handleEvent(TodoStarEvent event) {
        if (isDeleted())
            throw new InvalidTodoStateException("Cannot star a deleted Todo.");
        this.starred = event.isStarred();
    }

    public void updateDescription(UpdateTodoDescriptionCommand command) {
        applyChange(new TodoDescriptionUpdatedEvent(command.getTodoId(), this.version(), command.getDescription(), now()));
    }

    public void completeTodo(CompleteTodoCommand command) {
        applyChange(new TodoCompletedEvent(command.getTodoId(), version(), now()));
    }

    public void deleteTodo(DeleteTodoCommand command) {
        applyChange(new TodoDeletedEvent(command.getTodoId(), version(), now()));
    }

    public void starTodo(StarTodoCommand command) {
        applyChange(new TodoStarEvent(command.getTodoId(), version(), now(), true));
    }

    public void unStarTodo(UnStarTodoCommand command) {
        applyChange(new TodoStarEvent(command.getTodoId(), version(), now(), false));
    }
}
