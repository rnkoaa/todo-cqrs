package com.todo.cqrs.todo;

/**
 * Created by 6/21/17.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
