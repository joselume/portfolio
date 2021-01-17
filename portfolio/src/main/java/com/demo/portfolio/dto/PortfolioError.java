package com.demo.portfolio.dto;

import org.springframework.http.HttpStatus;

public class PortfolioError {
    private HttpStatus status;
    private String message;

    public PortfolioError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
