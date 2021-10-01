package com.mangement.converters;

public interface Converter {
    <T> T convert (Object sourceObject, Class<T> target);
    boolean canConvert(Class<?> source, Class<?> target);
    boolean canConvert(Object candidate, Class<?> target);
}
