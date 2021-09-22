package com.mangement.service.util.matchers;

public interface Matcher<T> {
    Boolean isMatching(T a, T b);
}
