package com.todo.cqrs.todo;

/**
 * Created on 6/23/2017.
 */
public class InvalidTodoStateException extends RuntimeException
{
    public InvalidTodoStateException(String message) {
        super(message);
    }
}
