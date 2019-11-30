package com.example.dev.backend.api.initializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.example.dev.backend.api.exception.commons.ExceptionMessageBuilder;

@Component
public class StaticContextInitializer {
	
	@Autowired
    private MessageSource messageSource;
	
    @PostConstruct
    public void init() {
    	ExceptionMessageBuilder.setMessageSource(messageSource);
    }
}
