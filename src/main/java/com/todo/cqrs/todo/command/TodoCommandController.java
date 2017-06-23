package com.todo.cqrs.todo.command;

import com.todo.cqrs.lib.CommandGateway;
import com.todo.cqrs.todo.TodoDto;
import com.todo.cqrs.todo.TodoId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created on 6/21/2017.
 */
@RestController
@RequestMapping("/todos")
public class TodoCommandController {

    private final CommandGateway commandGateway;

    public TodoCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @PostMapping({"", "/"})
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {
        TodoId todoId = TodoId.randomId();
        CreateTodoCommand createTodoCommand = CreateTodoCommand
                .builder()
                .todoId(todoId)
                .description(todoDto.getDescription())
                .build();

        commandGateway.send(createTodoCommand);
        return ResponseEntity.created(URI.create("/todos/aggregates/" + todoId.id)).build();
    }

    @PutMapping("/{todoId}/description")
    public ResponseEntity<?> updateTodoDescription(@PathVariable("todoId") String todoId,
                                                   @RequestBody TodoDto todoDto) {

        UpdateTodoDescriptionCommand updateCommand = UpdateTodoDescriptionCommand.builder()
                .description(todoDto.getDescription())
                .todoId(new TodoId(todoId))
                .build();
        commandGateway.send(updateCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable("todoId") String todoId) {

        DeleteTodoCommand updateCommand = DeleteTodoCommand.builder()
                .todoId(new TodoId(todoId))
                .build();
        commandGateway.send(updateCommand);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{todoId}/favorite")
    public ResponseEntity<?> favoriteTodo(@PathVariable("todoId") String todoId) {

        StarTodoCommand updateCommand = StarTodoCommand.builder()
                .todoId(new TodoId(todoId))
                .build();
        commandGateway.send(updateCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{todoId}/favorite")
    public ResponseEntity<?> removeTodoFavorite(@PathVariable("todoId") String todoId) {

        UnStarTodoCommand updateCommand = UnStarTodoCommand.builder()
                .todoId(new TodoId(todoId))
                .build();
        commandGateway.send(updateCommand);
        return ResponseEntity.noContent().build();
    }
}
