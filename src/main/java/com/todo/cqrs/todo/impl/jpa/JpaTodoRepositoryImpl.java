package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventService;
import com.todo.cqrs.todo.TodoAggregate;
import com.todo.cqrs.todo.TodoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Created by 6/26/17.
 */
@Service("todoRepository")
@Profile("jpa")
public class JpaTodoRepositoryImpl implements TodoRepository {


    private final DomainEventService domainEventService;

    JpaTodoRepositoryImpl(DomainEventService domainEventService) {
        this.domainEventService = domainEventService;
    }

    @Override
    public void save(TodoAggregate aggregateRoot) {
        //for now only save the events
        Objects.requireNonNull(aggregateRoot, "Aggregate root should not be null.");
        List<DomainEvent> domainEvents = aggregateRoot.getUncommittedEvents();
        domainEventService.save(domainEvents);


        //handle saving aggregate root later.
    }

    @Override
    public TodoAggregate load(String todoId, Class<TodoAggregate> aggregateType) {
        Objects.requireNonNull(todoId, "Id of Aggregate root should not be null.");
        return find(todoId).orElseThrow(() -> new IllegalArgumentException("Aggregate root does not exist"));
    }


    @Override
    public Optional<TodoAggregate> find(String todoId) {
        Objects.requireNonNull(todoId, "Id of Aggregate root should not be null.");
        TodoAggregate aggregateRoot = null;

        List<? extends DomainEvent> domainEvents = domainEventService.findByAggregate(todoId);
        if(domainEvents.size() > 0){
            try {
                aggregateRoot = new TodoAggregate();
                aggregateRoot.loadFromHistory((List<DomainEvent>) domainEvents);
            } catch (IllegalArgumentException iae) {
                String message = format("Aggregate of type [%s] does not exist, ID: %s", TodoAggregate.class.getSimpleName(), todoId);
                throw new IllegalArgumentException(message);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return Optional.ofNullable(aggregateRoot);
    }

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
