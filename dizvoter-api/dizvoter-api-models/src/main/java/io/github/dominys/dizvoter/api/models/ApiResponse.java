package io.github.dominys.dizvoter.api.models;

public record ApiResponse<T> (
    T data,
    ApiResponseError error
){

    public static <D> ApiResponse<D> empty() {
        return new ApiResponse<>(null, null);
    }

    public static <D> ApiResponse<D> ofData(D data) {
        return new ApiResponse<>(data, null);
    }

    public static <D> ApiResponse<D> ofError(ApiResponseError error) {
        return new ApiResponse<>(null, error);
    }

}