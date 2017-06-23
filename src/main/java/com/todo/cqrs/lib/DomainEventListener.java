package com.todo.cqrs.lib;

/**
 * Created by 6/21/17.
 */
public interface DomainEventListener {

    boolean supportsReplay();

}