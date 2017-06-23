package com.todo.cqrs.todo;

import com.todo.cqrs.lib.DomainEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 6/23/2017.
 */
@RestController
@RequestMapping("/todos/aggregates")
public class TodoAggregateController {

    private final TodoAggregateService todoAggregateService;

    public TodoAggregateController(TodoAggregateService todoAggregateService) {
        this.todoAggregateService = todoAggregateService;
    }

    @GetMapping({"", "/"})
    public List<TodoAggregate> todos() {
        return todoAggregateService.findAll();
    }

    @GetMapping("/{aggregateId}")
    public TodoAggregate findAggregate(@PathVariable("aggregateId") String aggregateId) {
        return todoAggregateService.find(aggregateId)
                .orElseThrow(() -> new NotFoundException("Todo with Id: " + aggregateId + " does not exist"));
    }

    @GetMapping("/{aggregateId}/events")
    public List<? extends DomainEvent> findAggregateEvents(@PathVariable("aggregateId") String aggregateId) {
        return todoAggregateService.getEvents(aggregateId);
    }
}
