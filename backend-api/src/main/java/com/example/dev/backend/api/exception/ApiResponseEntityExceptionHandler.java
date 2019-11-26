package com.example.dev.backend.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.dev.backend.api.constant.ApiResponseErrorConstant;
import com.example.dev.backend.api.exception.customs.NotFoundRequestException;
import com.example.dev.backend.api.exception.error.ApiError;
import com.example.dev.backend.api.exception.error.ApiSubError;

@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex,
				this.buildErrorApi(HttpStatus.BAD_REQUEST,
						this.getUserMessage(ApiResponseErrorConstant.INVALID_MESSAGE), ex),
				headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, this.buildErrorApi(HttpStatus.BAD_REQUEST, null, ex, ex.getBindingResult()),
				headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		return handleExceptionInternal(ex,
				this.buildErrorApi(HttpStatus.NOT_FOUND,
						this.getUserMessage(ApiResponseErrorConstant.RESOURCE_NOT_FOUND), ex),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		return handleExceptionInternal(ex,
				this.buildErrorApi(HttpStatus.BAD_REQUEST,
						this.getUserMessage(ApiResponseErrorConstant.OPERATION_NOT_PERMITTED), ex),
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ NotFoundRequestException.class })
	public ResponseEntity<Object> handleNotFoundRequestException(NotFoundRequestException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				this.buildErrorApi(HttpStatus.NOT_FOUND,
						this.getUserMessage(ApiResponseErrorConstant.RESOURCE_NOT_FOUND)),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	private ApiError buildErrorApi(final HttpStatus status, String userMessage, Exception ex) {
		return new ApiError(status, userMessage, ex);
	}

	private ApiError buildErrorApi(final HttpStatus status, String userMessage) {
		return new ApiError(status, userMessage);
	}

	private ApiError buildErrorApi(final HttpStatus status, String userMessage, Throwable ex,
			BindingResult bindingResult) {
		return new ApiError(status, ex, createListofSubErrors(bindingResult));
	}

	private List<ApiSubError> createListofSubErrors(BindingResult bindingResult) {
		List<ApiSubError> apiSubErrorList = new ArrayList<ApiSubError>();

		bindingResult.getFieldErrors()
				.forEach(item -> apiSubErrorList.add(new ApiSubError(item.getObjectName(), item.getField(),
						item.getRejectedValue(), messageSource.getMessage(item, LocaleContextHolder.getLocale()))));

		return apiSubErrorList;
	}

	private String getUserMessage(String keyMessage) {
		return messageSource.getMessage(keyMessage, null, LocaleContextHolder.getLocale());
	}
}
