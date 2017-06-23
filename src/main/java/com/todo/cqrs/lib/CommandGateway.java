package com.todo.cqrs.lib;

import com.todo.cqrs.lib.command.Command;
import com.todo.cqrs.lib.command.ResultValue;

/**
 * Created by 6/22/17.
 */
public interface CommandGateway {
    /**
     * @param command
     */
    void send(Command command);

    ResultValue sendAndWait(Command command);
}
