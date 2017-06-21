package com.todo.cqrs.todo;

import com.todo.cqrs.todo.query.TodoQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 6/21/2017.
 */
@RestController
@RequestMapping("/todos")
public class TodoQueryController {
    private final TodoQueryService todoQueryService;

    public TodoQueryController(TodoQueryService todoQueryService) {
        this.todoQueryService = todoQueryService;
    }

    @GetMapping({"", "/"})
    public List<Todo> getAll(){
        return todoQueryService.findAll();
    }
}
