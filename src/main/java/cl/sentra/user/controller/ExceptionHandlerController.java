package cl.sentra.user.controller;

import cl.sentra.user.dto.UserErrorResponse;
import cl.sentra.user.exception.BadRequestException;
import cl.sentra.user.exception.NotFoundException;
import cl.sentra.user.exception.UserExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody UserErrorResponse badRequestHandlerException(UserExistException e) {
        return new UserErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody UserErrorResponse badRequestHandlerException(BadRequestException e) {
        return new UserErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody UserErrorResponse badRequestHandlerException(Exception e) {
        return new UserErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody UserErrorResponse badRequestHandlerException(NotFoundException e) {
        return new UserErrorResponse(e.getMessage());
    }
}
