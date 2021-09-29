package com.mangement.service;

import com.mangement.transformers.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.Map;
import java.util.Set;

@Service
public class ConversionServiceImpl implements ConversionService {

    private ConvertorsRegistry registry;

    @Override
    public boolean canConvert(Class<?> target, Class<?> source) {
        return false;
    }

    @Override
    public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return false;
    }

    @Override
    public <T> T convert(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return null;
    }
}
