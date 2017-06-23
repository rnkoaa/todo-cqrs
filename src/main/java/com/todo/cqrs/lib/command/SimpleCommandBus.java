package com.todo.cqrs.lib.command;

/**
 * https://github.com/BottegaIT/ddd-cqrs-sample
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 6/23/2017.
 */
public class SimpleCommandBus implements CommandBus {
    private final String identifier;
    private Map<Class<? extends Command>, CommandHandler> handlers = new HashMap<>();

    public SimpleCommandBus() {
        this(null);
    }

    public SimpleCommandBus(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void register(Class<? extends Command> commandClass, CommandHandler handler) {
        handlers.putIfAbsent(commandClass, handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public  <C extends Command> void dispatch(C command) {
        Class clzz = command.getClass();
        CommandHandler commandHandler = handlers.get(clzz);
        if (commandHandler != null)
            commandHandler.handle(command);
    }

    @Override
    public void unRegister(Class<? extends Command> commandClass) {
        handlers.remove(commandClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends Command, R> R sendForResult(C command) {
        Class clzz = command.getClass();
        CommandHandler commandHandler = handlers.get(clzz);
        if (commandHandler == null) {
            throw new RuntimeException("Identifier: " + identifier + " command handler not found. Command class is " + command.getClass());
        }
        return (R) commandHandler.handle(command);
    }
/*
    @SuppressWarnings("unchecked")
    @Override
    public <C extends Command> void send(C command) {
        Class clzz = command.getClass();
        CommandHandler commandHandler = handlers.get(clzz);
        if (commandHandler != null)
            commandHandler.handle(command);
    }*/
}
