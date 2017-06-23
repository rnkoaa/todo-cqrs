package com.todo.cqrs.todo.command;

import com.google.common.eventbus.EventBus;
import com.todo.cqrs.lib.Command;
import com.todo.cqrs.lib.CommandSender;
import org.springframework.stereotype.Service;

/**
 * Created by 6/22/17.
 */
@Service("commandSender")
public class CommandSenderImpl implements CommandSender {
    private final EventBus commandEventBus;

    public CommandSenderImpl(EventBus commandEventBus) {
        this.commandEventBus = commandEventBus;
    }

    @Override
    public void send(Command command) {
        commandEventBus.post(command);
    }
}
