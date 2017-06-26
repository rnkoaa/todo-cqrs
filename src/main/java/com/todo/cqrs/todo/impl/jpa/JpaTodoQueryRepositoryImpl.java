package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.todo.query.TodoQueryObject;
import com.todo.cqrs.todo.query.TodoQueryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 6/26/17.
 */
@Service("todoQueryRepository")
@Profile("jpa")
public class JpaTodoQueryRepositoryImpl implements TodoQueryRepository {
    @Override
    public List<TodoQueryObject> findAll() {
        return null;
    }

    @Override
    public Optional<TodoQueryObject> find(String id) {
        return null;
    }
}
