package com.todo.cqrs.todo.impl;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoId;
import com.todo.cqrs.todo.TodoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 6/22/17.
 */
@Component("todoRepository")
@Profile("in-memory")
public class InMemoryTodoRepositoryImpl implements TodoRepository {
    private List<TodoAggregate> inMemoryTodoAggregates = new ArrayList<>();

    @Override
    public void save(TodoAggregate todoAggregate) {
        if (inMemoryTodoAggregates.size() > 0) {
            Optional<TodoAggregate> optionalExisting = find(todoAggregate.getId());
            optionalExisting.ifPresent(todoAggregate1 -> {
                List<DomainEvent> uncommittedEvents = todoAggregate1.getUncommittedEvents();
                todoAggregate.getUncommittedEvents().addAll(uncommittedEvents);
            });
            inMemoryTodoAggregates.removeIf(todoAggregate1 -> todoAggregate1.getId().equals(todoAggregate.getId()));
            inMemoryTodoAggregates.add(todoAggregate);
        } else
            inMemoryTodoAggregates.add(todoAggregate);
    }

    @Override
    public Optional<TodoAggregate> find(TodoId todoId) {
        return inMemoryTodoAggregates.stream()
                .filter(todoAggregate1 -> todoAggregate1.getId().equals(todoId.id))
                .findFirst();

    }

    @Override
    public Optional<TodoAggregate> find(String todoId) {
        return inMemoryTodoAggregates.stream()
                .filter(todoAggregate1 -> todoAggregate1.getId().equals(todoId))
                .map(todoAggregate -> TodoAggregate.builder()
                        .id(todoAggregate.id())
                        .description(todoAggregate.getDescription())
                        .deleted(todoAggregate.isDeleted())
                        .createdAt(todoAggregate.getCreatedAt())
                        .completedOn(todoAggregate.getCompletedOn())
                        .starred(todoAggregate.isStarred())
                        .build())
                .findFirst();

    }

    @Override
    public Optional<TodoAggregate> findWithEvents(String todoId) {
        return inMemoryTodoAggregates.stream()
                .filter(todoAggregate1 -> todoAggregate1.getId().equals(todoId))
                .findFirst();

    }

    @Override
    public List<TodoAggregate> allAggregates() {
        return inMemoryTodoAggregates
                .stream()
                .map(todoAggregate -> TodoAggregate.builder()
                        .id(todoAggregate.id())
                        .description(todoAggregate.getDescription())
                        .deleted(todoAggregate.isDeleted())
                        .createdAt(todoAggregate.getCreatedAt())
                        .completedOn(todoAggregate.getCompletedOn())
                        .starred(todoAggregate.isStarred())
                        .build())
                .collect(Collectors.toList());
    }

}
