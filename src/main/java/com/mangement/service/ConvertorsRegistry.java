package com.mangement.service;

import com.mangement.transformers.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ConvertorsRegistry {

    @Autowired
    private Map<String, Converter> converterMap;
    private Map<Class<?>, Class<?>> conversionMapping;

    @EventListener(classes = ContextRefreshedEvent.class)
    private void onContextRefreshEvent(){
        for (Converter converter : converterMap.values()) {
            Class<?> genericInterface = (Class<?>) converter.getClass().getGenericInterfaces()[0];
            genericInterface.get

        }
    }

    public Boolean isConvertible(Class<?> target, Class<?> source){
        Class<?> conversionCandidate = conversionMapping.get(target);
        if (conversionCandidate != null)
            return conversionCandidate.isAssignableFrom(source);

        return false;
    }

    public Converter getProperConverter(Class<?> convertibleClass){

    }

}
