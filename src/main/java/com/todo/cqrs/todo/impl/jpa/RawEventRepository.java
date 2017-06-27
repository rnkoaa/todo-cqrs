package com.todo.cqrs.todo.impl.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on 6/27/2017.
 */
@Profile("jpa")
public interface RawEventRepository extends JpaRepository<RawEvent, Long> {
    List<RawEvent> findByAggregateId(String aggregateId);
}
