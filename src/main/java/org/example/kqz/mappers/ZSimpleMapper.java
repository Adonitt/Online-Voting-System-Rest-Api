package org.example.kqz.mappers;

import java.util.List;

public interface ZSimpleMapper<TEntity, TDto> {
    TDto toDto(TEntity entity);

    TEntity toEntity(TDto dto);

    List<TDto> toDtoList(List<TEntity> entityList);

    List<TEntity> toEntityList(List<TDto> dtoList);
}
