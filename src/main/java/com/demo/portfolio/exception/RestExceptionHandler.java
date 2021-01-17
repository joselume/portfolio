package com.demo.portfolio.exception;

import com.demo.portfolio.dto.PortfolioError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({NoSuchPortfolioException.class})
    protected ResponseEntity<Object> handleNoSuchPortfolio(NoSuchPortfolioException exception){
        PortfolioError portfolioError = new PortfolioError(HttpStatus.NOT_FOUND, exception.getMessage());
        return buildResponseEntity(portfolioError);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String description = exception.getAllErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.joining(", "));

        PortfolioError portfolioError = new PortfolioError(HttpStatus.BAD_REQUEST,  "Validation errors: " + description);
        return buildResponseEntity(portfolioError);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(ResourceNotFoundException exception){

        PortfolioError portfolioError = new PortfolioError(HttpStatus.NOT_FOUND,  "The portfolio's twitter user was not found by the twitter API");
        return buildResponseEntity(portfolioError);
    }

    private ResponseEntity<Object> buildResponseEntity(PortfolioError portfolioError) {
        return new ResponseEntity<>(portfolioError, portfolioError.getStatus());
    }
}
