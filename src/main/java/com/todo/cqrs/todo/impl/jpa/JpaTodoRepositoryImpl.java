package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by 6/26/17.
 */
@Service("todoRepository")
@Profile("jpa")
public class JpaTodoRepositoryImpl implements TodoRepository {

    private final JpaDomainEventRepository domainEventRepository;

    JpaTodoRepositoryImpl(JpaDomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public void save(TodoAggregate aggregateRoot) {
        //for now only save the events
        Objects.requireNonNull(aggregateRoot, "Aggregate root should not be null.");
        List<JpaDomainEvent> unCommittedDomainEvents = aggregateRoot.getUncommittedEvents()
                .stream()
                .map(domainEvent -> JpaDomainEvent.builder()
                        .domainEvent(new TodoDomainEvent(domainEvent))
                        .aggregateId(domainEvent.getAggregateId())
                        .build())
                .collect(Collectors.toList());

        domainEventRepository.save(unCommittedDomainEvents);

        //handle saving aggregate root later.
    }

    @Override
    public TodoAggregate load(String todoId, Class<TodoAggregate> aggregateType) {
        Objects.requireNonNull(todoId, "Id of Aggregate root should not be null.");
        return find(todoId).orElseThrow(() -> new IllegalArgumentException("Aggregate root does not exist"));
    }

 /*   @Override
    public TodoAggregate load(TodoId todoId, Class<TodoAggregate> aggregateType) {
        Objects.requireNonNull(todoId, "Id of Aggregate root should not be null.");
        return find(todoId).orElseThrow(() -> new IllegalArgumentException("Aggregate root does not exist"));
    }*/

/*
    @Override
    public Optional<TodoAggregate> find(TodoId todoId) {
        Objects.requireNonNull(todoId, "Id of Aggregate root should not be null.");
        String aggregateId = todoId.id;
        return find(aggregateId);
    }
*/

    @Override
    public Optional<TodoAggregate> find(String todoId) {
        Objects.requireNonNull(todoId, "Id of Aggregate root should not be null.");
        TodoAggregate aggregateRoot = null;
        List<JpaDomainEvent> jpaDomainEvents = domainEventRepository.findByAggregateId(todoId);
        if (jpaDomainEvents.size() > 0) {
            List<DomainEvent> domainEvents = jpaDomainEvents.stream()
                    .map(jpaDomainEvent -> (DomainEvent) jpaDomainEvent.getDomainEvent())
                    .collect(Collectors.toList());

            try {
                aggregateRoot = new TodoAggregate();
                aggregateRoot.loadFromHistory(domainEvents);
            } catch (IllegalArgumentException iae) {
                String message = format("Aggregate of type [%s] does not exist, ID: %s", TodoAggregate.class.getSimpleName(), todoId);
                throw new IllegalArgumentException(message);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return Optional.ofNullable(aggregateRoot);
    }

/*
    @Override
    public Optional<TodoAggregate> findFromHistory(TodoId todoId) {
        return null;
    }
*/

    @Override
    public Optional<TodoAggregate> findFromHistory(String todoId) {
        return null;
    }

    @Override
    public Optional<TodoAggregate> findWithEvents(String todoId) {
        return null;
    }

    @Override
    public List<TodoAggregate> allAggregates() {
        return null;
    }
}
