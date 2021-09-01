package com.bank.transformer;


public interface Transformer <Entity, Dto>{
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
}
