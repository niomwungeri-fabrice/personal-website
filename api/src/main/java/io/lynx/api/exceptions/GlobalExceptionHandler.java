package io.lynx.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> handleGenericException(InternalServerErrorException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        return buildErrorResponse("an unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, path);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(BadRequestException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, path);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        String message = extractErrorMessageFromConstraintViolation(ex);
        return buildErrorResponse(message, HttpStatus.BAD_REQUEST, path);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String error, HttpStatus status, String path) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(error)
                .status(status.value())
                .path(path)
                .timestamp(new java.util.Date())
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }

    private String extractErrorMessageFromConstraintViolation(ConstraintViolationException ex) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            errorMessageBuilder.append(violation.getMessage()).append("; ");
        }

        String errorMessage = errorMessageBuilder.toString();
        if (!errorMessage.isEmpty()) {
            // Remove the trailing semicolon and space
            errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
        } else {
            errorMessage = "Invalid request data";
        }

        return errorMessage;
    }
}
