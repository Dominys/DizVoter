package io.github.dominys.dizvoter.api.models;

import lombok.Builder;

import java.util.List;

@Builder
public record ApiResponseError (
    String message,
    List<ApiFieldError> fieldErrors
) {

  public ApiResponseError(String message, List<ApiFieldError> fieldErrors) {
    this.message = message;
    this.fieldErrors = fieldErrors == null ? List.of() : fieldErrors;
  }
}
