package com.todo.cqrs.todo.query;

import com.todo.cqrs.todo.Todo;

import java.util.List;

/**
 * Created on 6/21/2017.
 */
public interface TodoQueryService {
    List<Todo> findAll();
}
