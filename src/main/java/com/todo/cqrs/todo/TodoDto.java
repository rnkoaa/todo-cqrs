package com.todo.cqrs.todo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created on 6/21/2017.
 */
@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = TodoDto.TodoDtoBuilder.class)
public class TodoDto {
    private String description;


    @JsonPOJOBuilder(withPrefix = "")
    public static class TodoDtoBuilder{

    }
}
