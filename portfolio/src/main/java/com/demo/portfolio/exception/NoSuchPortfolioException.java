package com.demo.portfolio.exception;

public class NoSuchPortfolioException extends RuntimeException{
    public NoSuchPortfolioException(String message) {
        super(message);
    }
}
