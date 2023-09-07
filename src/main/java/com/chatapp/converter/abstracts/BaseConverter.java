package com.chatapp.converter.abstracts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<Entity, DTO> {
    @Autowired
    private ModelMapper modelMapper;

    public DTO toDTO(Entity entity) {
        Type dtoClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        DTO dto = modelMapper.map(entity, dtoClass);
        return dto;
    }

    public Entity toEntity(DTO dto) {
        Type entityClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Entity entity = modelMapper.map(dto, entityClass);
        return entity;
    }

    public List<DTO> toDTOGroup(List<Entity> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<Entity> toEntityGroup(List<DTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }
}
