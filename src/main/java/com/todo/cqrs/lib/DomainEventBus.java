package com.todo.cqrs.lib;

import java.util.List;

/**
 * Created by 6/21/17.
 */
public interface DomainEventBus {

    void publish(List<DomainEvent> events);

    void republish(List<DomainEvent> events);

    <T extends DomainEventListener> T register(T listener);
}
