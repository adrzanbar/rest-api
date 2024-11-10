package com.uncode.stop.rest_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;

@ControllerAdvice
@ResponseBody
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(NotFoundException e) {
    return "Resource not found";
  }

  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public String handleServiceException(ServiceException e) {
    return e.getMessage();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleException(Exception e) {
    return "Internal server error";
  }
}
