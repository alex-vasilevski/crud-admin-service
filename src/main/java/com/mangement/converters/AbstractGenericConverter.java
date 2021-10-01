package com.mangement.converters;

import org.springframework.lang.Nullable;

public abstract class AbstractGenericConverter<Entity, Dto> implements Converter{

    private Class<Entity> entityClass;
    private Class<Dto> dtoClass;

    @Override
    @Nullable
    public <T> T convert(Object sourceObject, Class<T> target) {
        if(sourceObject == null || target == null){
            return null;
        }

        if(canConvert(sourceObject, target)){
            if (target.isAssignableFrom(entityClass)){
                Entity entity = toEntity(dtoClass.cast(sourceObject));
                return target.cast(entity);
            }
            else if(target.isAssignableFrom(dtoClass)){
                Dto dto = toDto(entityClass.cast(sourceObject));
                return target.cast(dto);
            }
        }
        return null;
    }

    @Override
    public boolean canConvert(Class<?> source, Class<?> target) {
        return (source.isAssignableFrom(entityClass) && target.isAssignableFrom(dtoClass)) ||
                (source.isAssignableFrom(dtoClass) && target.isAssignableFrom(entityClass));
    }

    @Override
    public boolean canConvert(Object candidate, Class<?> target) {
        Class<?> candidateClass = candidate.getClass();
        return canConvert(candidateClass, target);
    }

    protected abstract Dto toDto(Entity entity);
    protected abstract Entity toEntity(Dto dto);
}
