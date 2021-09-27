package com.mangement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class PageableProducer {

    @Value("${employee.service.default-sort-direction}")
    private String defaultSortDirection;

    @Value("${employee.service.default-sort-parameter}")
    private String defaultSortParameter;

    @Value("${employee.service.page-size}")
    private Integer pageSize;

    public Pageable produce(String direction, Set<String> sortParams){
        if (direction == null)
            direction = defaultSortDirection;

        if(sortParams == null)
            sortParams = Collections.singleton(defaultSortParameter);

        String[] params = sortParams.toArray(new String[0]);

        Sort sort = Sort.by(Sort.Direction.valueOf(direction), params);
        return PageRequest.ofSize(pageSize).withSort(sort);
    }
}
