package com.todo.cqrs.todo.query;

import com.todo.cqrs.todo.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 6/21/17.
 */
@ControllerAdvice
public class TodoExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorResponse handleNotFound(HttpServletRequest req, NotFoundException ex) {
        return ErrorResponse.builder()
                .url(req.getRequestURL().toString())
                .message(ex.getLocalizedMessage())
                .build();
    }
}
