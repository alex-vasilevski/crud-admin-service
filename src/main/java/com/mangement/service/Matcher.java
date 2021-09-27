package com.mangement.service;

public interface Matcher<T> {
    Boolean isMatching(T a, T b);
}
