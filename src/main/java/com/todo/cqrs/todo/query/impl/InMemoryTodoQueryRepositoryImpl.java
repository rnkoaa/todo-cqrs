package com.todo.cqrs.todo.query.impl;

import com.google.common.collect.Lists;
import com.todo.cqrs.todo.Todo;
import com.todo.cqrs.todo.query.TodoQueryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 6/21/2017.
 */
@Service("todoQueryRepository")
@Profile("in-memory")
public class InMemoryTodoQueryRepositoryImpl implements TodoQueryRepository {
    private AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public List<Todo> findAll() {
        return Lists.newArrayList(
                Todo.builder()
                        .id(atomicLong.incrementAndGet())
                        .createdAt(LocalDateTime.now())
                        .description("Build Kubecluser")
                        .build(),
                Todo.builder()
                        .id(atomicLong.incrementAndGet())
                        .createdAt(LocalDateTime.now())
                        .description("Build update cluster config")
                        .build(),
                Todo.builder()
                        .id(atomicLong.incrementAndGet())
                        .createdAt(LocalDateTime.now())
                        .description("call mommy")
                        .build()
        );
    }
}
