package pers.ervinse.handler.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.ervinse.enums.ResponseCode;
import pers.ervinse.exception.SystemException;
import pers.ervinse.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ApiResponse systemExceptionHandler(SystemException e) {
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse exceptionHandler(Exception e) {
        return ApiResponse.fail(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
