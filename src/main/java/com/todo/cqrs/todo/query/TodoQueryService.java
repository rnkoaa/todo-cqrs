package com.todo.cqrs.todo.query;

import java.util.List;
import java.util.Optional;

/**
 * Created on 6/21/2017.
 */
public interface TodoQueryService {
    List<TodoQueryObject> findAll();

    Optional<TodoQueryObject> find(String id);
}
