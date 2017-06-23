package com.todo.cqrs.lib;

/**
 * Created by 6/22/17.
 */
public interface CommandSender {
    /**
     * @param command
     */
    void send(Command command);
}
