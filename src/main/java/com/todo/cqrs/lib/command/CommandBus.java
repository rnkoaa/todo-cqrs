package com.todo.cqrs.lib.command;

/**
 * Created on 6/23/2017.
 */
public interface CommandBus {
    <C extends Command> void dispatch(C command);

    void register(Class<? extends Command> commandClass, CommandHandler handler);

    void unRegister(Class<? extends Command> commandClass);

    <C extends Command, R> R sendForResult(C command);

}
