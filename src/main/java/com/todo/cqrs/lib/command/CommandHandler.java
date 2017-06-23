package com.todo.cqrs.lib.command;

/**
 * Created on 6/23/2017.
 */
public interface CommandHandler<C, R> {

    R handle(C command);
}
