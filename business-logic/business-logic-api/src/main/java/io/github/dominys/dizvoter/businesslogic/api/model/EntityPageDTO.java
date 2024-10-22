package io.github.dominys.dizvoter.businesslogic.api.model;

import java.util.List;

public record EntityPageDTO<T> (
    List<T> data,
    long totalElements)
{

}