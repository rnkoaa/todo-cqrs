package com.todo.cqrs.todo;

import com.todo.cqrs.todo.query.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 6/23/2017.
 */
@ControllerAdvice
public class TodoGlobalExceptionHandler {

    @ExceptionHandler({InvalidTodoStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidTodoState(HttpServletRequest req, InvalidTodoStateException ex) {
        return ErrorResponse.builder()
                .message(ex.getLocalizedMessage())
                .url(req.getRequestURL().toString())
                .build();
    }
}
