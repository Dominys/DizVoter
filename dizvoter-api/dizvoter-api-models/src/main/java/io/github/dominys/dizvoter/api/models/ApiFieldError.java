package io.github.dominys.dizvoter.api.models;

import lombok.Builder;

@Builder
public record ApiFieldError (
    String fieldName,
    String fieldError
){

}
