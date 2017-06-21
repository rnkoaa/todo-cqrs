package com.todo.cqrs.todo.query.impl;

import com.todo.cqrs.todo.Todo;
import com.todo.cqrs.todo.query.TodoQueryRepository;
import com.todo.cqrs.todo.query.TodoQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 6/21/2017.
 */
@Service
public class TodoQueryServiceImpl implements TodoQueryService {
    private final TodoQueryRepository todoQueryRepository;

    public TodoQueryServiceImpl(TodoQueryRepository todoQueryRepository) {
        this.todoQueryRepository = todoQueryRepository;
    }

    @Override
    public List<Todo> findAll() {
        return todoQueryRepository.findAll();
    }
}
