package com.todo.cqrs.todo.query.impl;

import com.todo.cqrs.todo.query.TodoQueryObject;
import com.todo.cqrs.todo.query.TodoQueryRepository;
import com.todo.cqrs.todo.query.TodoQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<TodoQueryObject> findAll() {
        return todoQueryRepository.findAll();
    }

    @Override
    public Optional<TodoQueryObject> find(String id) {
        return todoQueryRepository.find(id);
    }
}
