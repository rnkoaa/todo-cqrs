package com.todo.cqrs.lib;

/**
 * Created on 6/21/2017.
 */
public interface CommandBus {

    void send(Command command);
}
