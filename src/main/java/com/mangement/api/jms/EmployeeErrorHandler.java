package com.mangement.api.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class EmployeeErrorHandler implements ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeErrorHandler.class);
    @Override
    public void handleError(Throwable throwable) {
        logger.warn("error occurred " + throwable.getMessage());
    }
}
