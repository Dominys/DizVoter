export interface ApiResponse<T> {
    data?: T;
    error?: ApiResponseError;
}

export interface ApiResponseError {
    message: string;
    fieldErrors: [];
}

export interface ApiPage<T> {
    data: T[];
    totalElements: number;
}