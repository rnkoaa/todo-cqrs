package com.todo.cqrs.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

/**
 * Created on 6/21/2017.
 */
@Data
@Builder
@AllArgsConstructor
public class Todo {
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedOn;

    //for JPA
    @Tolerate
    public Todo() {
    }
}
