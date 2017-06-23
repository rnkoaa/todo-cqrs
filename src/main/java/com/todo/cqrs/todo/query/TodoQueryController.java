package com.todo.cqrs.todo.query;

import com.todo.cqrs.todo.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 6/21/2017.
 */
@RestController
@RequestMapping("/todos")
public class TodoQueryController {
    private static Logger logger = LoggerFactory.getLogger(TodoQueryController.class);

    private final TodoQueryService todoQueryService;

    public TodoQueryController(TodoQueryService todoQueryService) {
        this.todoQueryService = todoQueryService;
    }

    @GetMapping({"", "/"})
    public List<TodoQueryObject> getAll() {
        logger.info("finding All TodoAggregate's");
        return todoQueryService.findAll();
    }

    @GetMapping({"/{todoId}"})
    public TodoQueryObject findOneTodo(@PathVariable("todoId") String todoId) {
        logger.info("finding todo with id: {}", todoId);
        return todoQueryService
                .find(todoId)
                .orElseThrow(() ->
                        new NotFoundException("TodoAggregate Id: " + todoId + " was not found."));
    }
}
