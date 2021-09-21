package com.mangement.api.transformers.spi;

public interface Transformer<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity (Dto dto);
}
