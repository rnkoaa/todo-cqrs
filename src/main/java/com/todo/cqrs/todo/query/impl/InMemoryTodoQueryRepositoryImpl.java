package com.todo.cqrs.todo.query.impl;

import com.google.common.collect.Lists;
import com.todo.cqrs.todo.query.TodoQueryObject;
import com.todo.cqrs.todo.query.TodoQueryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Created on 6/21/2017.
 */
@Service("todoQueryRepository")
@Profile("in-memory")
public class InMemoryTodoQueryRepositoryImpl implements TodoQueryRepository {
    //private AtomicLong atomicLong = new AtomicLong(0);
    private List<TodoQueryObject> todoQueryObjects;

    InMemoryTodoQueryRepositoryImpl() {
        todoQueryObjects = Lists.newArrayList(
                TodoQueryObject.builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(LocalDateTime.now())
                        .description("Build Kubecluser")
                        .build(),
                TodoQueryObject.builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(LocalDateTime.now())
                        .description("Build update cluster config")
                        .build(),
                TodoQueryObject.builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(LocalDateTime.now())
                        .description("call mommy")
                        .build());
    }

    @Override
    public List<TodoQueryObject> findAll() {
        return todoQueryObjects;
    }

    @Override
    public Optional<TodoQueryObject> find(String id) {
        return todoQueryObjects.stream()
                .filter(todo ->
                        Objects.equals(todo.getId(), id))
                .findFirst();
    }
}
