package com.todo.cqrs.todo.query;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by 6/22/17.
 */
@Builder
@Data
public class TodoQueryObject {
    private String id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedOn;
}
