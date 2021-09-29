package com.mangement.transformers;

public interface Converter<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity (Dto dto);
}
