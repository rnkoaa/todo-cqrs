package com.todo.cqrs.todo.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

/**
 * Created by 6/21/17.
 */
@Value
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private final String url;
    private final String message;
}
