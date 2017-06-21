package com.todo.cqrs.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created on 6/21/2017.
 */
@Data
@Builder
@AllArgsConstructor
public class TodoDto {
    private String description;
}
