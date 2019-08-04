package com.example.dev.backend.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.dev.backend.api.exception.error.ApiError;
import com.example.dev.backend.api.exception.error.ApiSubError;

@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	private String MENSAGEM_INVALIDA = "invalid.message";
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex,
				this.buildErrorApi(HttpStatus.BAD_REQUEST, this.getUserMessage(MENSAGEM_INVALIDA), ex), headers,
				HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, this.buildErrorApi(HttpStatus.BAD_REQUEST,
				null, ex, ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST,
				request);
	}
	
	private ApiError buildErrorApi(HttpStatus status, String userMessage, Throwable ex) {
		List<ApiSubError> apiSubErrorList = new ArrayList<ApiSubError>();
		apiSubErrorList.add(new ApiSubError(null, null, null, userMessage));
		return new ApiError(HttpStatus.BAD_REQUEST, userMessage, ex, apiSubErrorList);
	}

	private ApiError buildErrorApi(HttpStatus status, String userMessage, Throwable ex, BindingResult bindingResult) {
		List<ApiSubError> apiSubErrorList = createListofSubErrors(bindingResult);
		return new ApiError(HttpStatus.BAD_REQUEST, userMessage, ex, apiSubErrorList);
	}
	
	private List<ApiSubError> createListofSubErrors(BindingResult bindingResult) {
		List<ApiSubError> apiSubErrorList = new ArrayList<ApiSubError>();

		bindingResult.getFieldErrors().forEach(item -> apiSubErrorList.add(
				new ApiSubError(
						item.getObjectName(),
						item.getField(), 
						item.getRejectedValue(), 
						messageSource.getMessage(item, LocaleContextHolder.getLocale()
		))));

		return apiSubErrorList;
	}
	
	private String getUserMessage (String keyMessage) {
		return messageSource.getMessage(keyMessage, null, LocaleContextHolder.getLocale());
	}
}
