package com.mangement.service;

import com.mangement.converters.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    private Set<Converter> converters;

    @Override
    public boolean canConvert(Class<?> source, Class<?> target) {
        return converters.stream().anyMatch(converter -> converter.canConvert(target, source));
    }

    @Override
    public boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return false;
    }

    @Override
    public <T> T convert(Object o, Class<T> target) {
        Optional<Converter> optional = converters
                .stream()
                .filter(converter -> converter.canConvert(o, target))
                .findFirst();

        if (optional.isPresent()){
            Converter converter = optional.get();
            return converter.convert(o, target);
        }

        return null;
    }

    @Override
    public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        return null;
    }
}
