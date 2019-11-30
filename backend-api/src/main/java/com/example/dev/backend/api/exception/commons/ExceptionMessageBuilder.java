package com.example.dev.backend.api.exception.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.example.dev.backend.api.exception.error.ApiError;
import com.example.dev.backend.api.exception.error.ApiSubError;

public final class ExceptionMessageBuilder {

	private static MessageSource messageSource;

	public static ApiError buildErrorApi(final HttpStatus status, String userMessage, String debugMessage) {
		return new ApiError(status, userMessage, debugMessage);
	}

	public static ApiError buildErrorApi(final HttpStatus status, String userMessage, Exception ex) {
		return new ApiError(status, userMessage, ex);
	}

	public static ApiError buildErrorApi(final HttpStatus status, String userMessage) {
		return new ApiError(status, userMessage);
	}

	public static ApiError buildErrorApi(final HttpStatus status, String userMessage, Throwable ex,
			BindingResult bindingResult) {
		return new ApiError(status, ex, createListofSubErrors(bindingResult));
	}

	public static String getUserMessage(String keyMessage) {
		return messageSource.getMessage(keyMessage, null, LocaleContextHolder.getLocale());
	}

	private static List<ApiSubError> createListofSubErrors(BindingResult bindingResult) {
		List<ApiSubError> apiSubErrorList = new ArrayList<ApiSubError>();
		bindingResult.getFieldErrors()
				.forEach(item -> apiSubErrorList.add(new ApiSubError(item.getObjectName(), item.getField(),
						item.getRejectedValue(), messageSource.getMessage(item, LocaleContextHolder.getLocale()))));

		return apiSubErrorList;
	}
	
	public static void setMessageSource (MessageSource messageSource) {
		ExceptionMessageBuilder.messageSource = messageSource;
	}
}
