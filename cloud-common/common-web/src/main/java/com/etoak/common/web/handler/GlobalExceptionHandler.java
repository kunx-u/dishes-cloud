package com.etoak.common.web.handler;

import com.etoak.common.core.vo.ResultVO;
import com.etoak.common.web.exception.CustomException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResultVO<Object> handler(MethodArgumentNotValidException e){
        log.error(e.getMessage(),e);
        String message = e.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return ResultVO.failed(message);
    }

    @ExceptionHandler
    public ResultVO<Object> handle(ConstraintViolationException e){
        log.error(e.getMessage());
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
        return ResultVO.failed(message);
    }
    @ExceptionHandler
    public ResultVO<Object> handle(CustomException e){
        log.error(e.getMessage(),e);
        return ResultVO.failed(e.getMessage());
    }

    @ExceptionHandler
    public ResultVO handle(Exception e){
        log.error(e.getMessage(),e);
        return ResultVO.failed("系统异常");
    }

}
