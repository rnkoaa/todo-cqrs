package com.todo.cqrs.todo.impl.inmemory;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventStore;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import com.todo.cqrs.todo.impl.jpa.TodoDomainEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by 6/22/17.
 */
@Component("todoRepository")
@Profile("in-memory")
public class InMemoryTodoRepositoryImpl implements TodoRepository {
    private final DomainEventStore domainEventStore;
    private List<TodoAggregate> inMemoryTodoAggregates = new ArrayList<>();

    public InMemoryTodoRepositoryImpl(DomainEventStore domainEventStore) {
        this.domainEventStore = domainEventStore;
    }

    //@Override
    private void saveAggregate(TodoAggregate todoAggregate) {
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

    /* @Override
     public Optional<TodoAggregate> find(TodoId todoId) {
         return inMemoryTodoAggregates.stream()
                 .filter(todoAggregate1 -> todoAggregate1.getId().equals(todoId.id))
                 .findFirst();

     }
 */
    @Override
    public Optional<TodoAggregate> find(String todoId) {
        return inMemoryTodoAggregates.stream()
                .filter(todoAggregate1 -> todoAggregate1.getId().equals(todoId))
                .map(todoAggregate -> TodoAggregate.builder()
                        .id(todoAggregate.getId())
                        .description(todoAggregate.getDescription())
                        .deleted(todoAggregate.isDeleted())
                        .createdAt(todoAggregate.getCreatedAt())
                        .completedOn(todoAggregate.getCompletedOn())
                        .starred(todoAggregate.isStarred())
                        .build())
                .findFirst();

    }

    @Override
    public Optional<TodoAggregate> findFromHistory(String todoId) {
        TodoAggregate todoAggregate = load(todoId, TodoAggregate.class);
        return Optional.ofNullable(todoAggregate);
    }

  /*  @Override
    public Optional<TodoAggregate> findFromHistory(String todoId) {
        Objects.requireNonNull(todoId, "There must be a valid todoid object");

       *//* TodoId id = new TodoId(todoId);*//*
        return findFromHistory(todoId);
    }*/

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

    @Override
    public void save(TodoAggregate todoAggregate) {
        if (todoAggregate.hasUncommittedEvents()) {
            List<DomainEvent> uncommittedEvents = todoAggregate.getUncommittedEvents();
            // List<TodoDomainEvent> todoDomainEvents = uncommittedEvents.stream().map(domainEvent -> (TodoDomainEvent) domainEvent).collect(Collectors.toList());

            domainEventStore.save(todoAggregate.id(), todoAggregate.getClass(), uncommittedEvents);
            //publish events

            saveAggregate(todoAggregate);
        }
    }


    @Override
    public TodoAggregate load(String id, Class<TodoAggregate> aggregateType) {
        try {
            TodoAggregate aggregateRoot = aggregateType.newInstance();
            List<DomainEvent> domainEvents = domainEventStore.loadEvents(id);
            //List<TodoDomainEvent> todoDomainEvents = domainEvents.stream().map(domainEvent -> (TodoDomainEvent) domainEvent).collect(Collectors.toList());
            aggregateRoot.loadFromHistory(domainEvents);
            return aggregateRoot;
        } catch (IllegalArgumentException iae) {
            String message = format("Aggregate of type [%s] does not exist, ID: %s", aggregateType.getSimpleName(), id);
            throw new IllegalArgumentException(message);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
