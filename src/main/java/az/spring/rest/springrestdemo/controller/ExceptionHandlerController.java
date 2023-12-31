package az.spring.rest.springrestdemo.controller;

import az.spring.rest.springrestdemo.enums.ErrorCodeEnum;
import az.spring.rest.springrestdemo.excpetion.CustomRestException;
import az.spring.rest.springrestdemo.rest.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CustomRestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(CustomRestException e){
        return ErrorResponse.builder().
                code(e.getCode())
                .message(e.getMessage()).
                build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentTypeMismatchException e){
        String parametr=e.getParameter().getParameter().getName();
        return ErrorResponse.builder().
                code(ErrorCodeEnum.VALIDATION_ERROR.getCode()).
                message(parametr + ErrorCodeEnum.VALIDATION_ERROR.getMessage()).
                build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInsert(MethodArgumentNotValidException e){
       String fieldName =  e.getBindingResult().getFieldError().getField();
        return ErrorResponse.builder().
                code(ErrorCodeEnum.VALIDATION_ERROR.getCode()).
                message(fieldName+ErrorCodeEnum.VALIDATION_ERROR.getMessage()).
                build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknown(Exception e){

        return ErrorResponse.builder().
                code(ErrorCodeEnum.UNKNOWN_VALIDIATION_ERROR.getCode()).
                message(ErrorCodeEnum.UNKNOWN_VALIDIATION_ERROR.getMessage()).
                build();
    }
}
