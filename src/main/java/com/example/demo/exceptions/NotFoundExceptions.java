package com.example.demo.exceptions;

import com.example.demo.constant.DefaultConstant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions() {super(DefaultConstant.NOT_FOUND);}
}
