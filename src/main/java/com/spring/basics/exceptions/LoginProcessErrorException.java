package com.spring.basics.exceptions;


public class LoginProcessErrorException extends Exception{
    public LoginProcessErrorException(String message) {
        super(message);
    }
}
