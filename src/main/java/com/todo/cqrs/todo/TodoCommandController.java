package com.todo.cqrs.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Created on 6/21/2017.
 */
@RestController
@RequestMapping("/todos")
public class TodoCommandController {
    @PostMapping({"", "/"})
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {

        return ResponseEntity.created(URI.create("")).build();
    }
}
