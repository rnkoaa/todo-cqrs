package com.todo.cqrs.todo.command.impl;

import com.todo.cqrs.lib.CommandGateway;
import com.todo.cqrs.lib.command.Command;
import com.todo.cqrs.lib.command.CommandBus;
import com.todo.cqrs.lib.command.ResultValue;
import org.springframework.stereotype.Service;

/**
 * Created by 6/22/17.
 */
@Service("commandSender")
public class CommandGatewayImpl implements CommandGateway {

    private final CommandBus commandBus;

    public CommandGatewayImpl(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void send(Command command) {
        commandBus.dispatch(command);
    }

    @Override
    public ResultValue sendAndWait(Command command) {
        return commandBus.sendForResult(command);
    }
}
