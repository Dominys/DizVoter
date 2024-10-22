package io.github.dominys.dizvoter.api.models;

import java.util.List;

public record ApiPage<T> (
    List<T> data,
    long totalElements)
{
}