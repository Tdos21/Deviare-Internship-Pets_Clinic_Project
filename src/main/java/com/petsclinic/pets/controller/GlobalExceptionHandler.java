package com.petsclinic.pets.controller;

import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", "Something went wrong!"); // You can customize this message
        model.addAttribute("exception", ex.getMessage());
        model.addAttribute("stackTrace", Arrays.toString(ex.getStackTrace()));
        return "error"; // Refers to error.html
    }
    
    
}