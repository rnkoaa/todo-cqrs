package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.lib.DomainEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 6/24/17.
 */
@RestController
@RequestMapping("/events")
public class DomainEventsController {
    private final Logger LOGGER = LoggerFactory.getLogger(DomainEventsController.class);
    private final DomainEventService domainEventService;

    public DomainEventsController(DomainEventService domainEventService) {
        this.domainEventService = domainEventService;
    }

    @GetMapping({"", "/"})
    public List<DomainEvent> showAll() {
        return domainEventService.findAll();
    }

    @GetMapping("/{aggregateId}")
    public List<DomainEvent> showForAggregate(@PathVariable("aggregateId") String aggregateId) {
        return domainEventService.findByAggregate(aggregateId);
    }
}
