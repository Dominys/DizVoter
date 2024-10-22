package io.github.dominys.dizvoter.application.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.dominys.dizvoter.api.models.ApiFieldError;
import io.github.dominys.dizvoter.api.models.ApiResponse;
import io.github.dominys.dizvoter.api.models.ApiResponseError;
import io.github.dominys.dizvoter.businesslogic.api.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import static io.github.dominys.dizvoter.api.models.ErrorMessages.GENERAL_ERROR_MESSAGE;
import static io.github.dominys.dizvoter.api.models.ErrorMessages.INVALID_REQUEST_BODY_ERROR_MESSAGE;


public class ApiExceptionHandler {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ApiResponse<Void> handleNorReadableException(RecordNotFoundException e) {
        return ApiResponse.ofError(ApiResponseError.builder()
            .message(e.getMessage())
            .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ApiResponse<Void> handleNorReadableException() {
        return ApiResponse.ofError(ApiResponseError.builder()
            .message(INVALID_REQUEST_BODY_ERROR_MESSAGE)
            .build());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleGeneralException(Exception ex) {
        log.error("Error occurred during processing request", ex);
        return ApiResponse.ofError(ApiResponseError.builder()
            .message(GENERAL_ERROR_MESSAGE)
            .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var result = ex.getBindingResult();
        var fieldErrors = result.getFieldErrors();
        return ApiResponse.ofError(ApiResponseError.builder()
            .message(INVALID_REQUEST_BODY_ERROR_MESSAGE)
            .fieldErrors(processFieldErrors(fieldErrors, ((BeanPropertyBindingResult) ex.getBindingResult()).getPropertyAccessor()))
            .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> constraintViolationException(ConstraintViolationException ex) {
        var violations = ex.getConstraintViolations();
        return ApiResponse.ofError(ApiResponseError.builder()
            .message(INVALID_REQUEST_BODY_ERROR_MESSAGE)
            .fieldErrors(processFieldErrors(violations))
            .build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ApiResponse.ofError(ApiResponseError.builder()
            .message(INVALID_REQUEST_BODY_ERROR_MESSAGE)
            .fieldErrors(List.of(ApiFieldError.builder()
                .fieldName(ex.getParameterName())
                .fieldError(ex.getMessage())
                .build()))
            .build());
    }

    private List<ApiFieldError> processFieldErrors(
        Collection<FieldError> fieldErrors, ConfigurablePropertyAccessor propertyAccessor) {
        return fieldErrors.stream()
            .map(f -> {
                String fieldName = Optional.ofNullable(propertyAccessor.getPropertyTypeDescriptor(f.getField()))
                        .map(t -> t.getAnnotation(JsonProperty.class))
                        .map(JsonProperty::value)
                        .orElse(f.getField());
                return ApiFieldError.builder()
                        .fieldName(fieldName)
                        .fieldError(f.getDefaultMessage())
                        .build();
            })
            .toList();
    }

    private List<ApiFieldError> processFieldErrors(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
            .map(f -> {
                var fieldName = StreamSupport.stream(f.getPropertyPath().spliterator(), false)
                        .reduce((first, second) -> second)
                        .map(Path.Node::getName)
                        .orElse(null);
                return ApiFieldError.builder()
                        .fieldName(fieldName)
                        .fieldError(f.getMessage())
                        .build();
            })
            .toList();
    }

}