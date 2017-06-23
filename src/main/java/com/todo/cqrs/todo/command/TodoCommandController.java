package com.todo.cqrs.todo.command;

import com.todo.cqrs.lib.CommandSender;
import com.todo.cqrs.todo.TodoDto;
import com.todo.cqrs.todo.TodoId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * Created on 6/21/2017.
 */
@RestController
@RequestMapping("/todos")
public class TodoCommandController {

    private final CommandSender commandSender;

    public TodoCommandController(CommandSender commandSender) {
        this.commandSender = commandSender;
    }


    @PostMapping({"", "/"})
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {
        TodoId todoId = TodoId.randomId();
        CreateTodoCommand createTodoCommand = CreateTodoCommand
                .builder()
                .todoId(todoId)
                .description(todoDto.getDescription())
                .build();

        commandSender.send(createTodoCommand);
        return ResponseEntity.created(URI.create("")).build();
    }
}
