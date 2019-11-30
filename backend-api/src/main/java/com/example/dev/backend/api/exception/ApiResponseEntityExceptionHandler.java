package com.example.dev.backend.api.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.dev.backend.api.constant.ApiResponseErrorConstant;
import com.example.dev.backend.api.exception.commons.ExceptionMessageBuilder;
import com.example.dev.backend.api.exception.customs.NotFoundRequestException;

@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex,
				ExceptionMessageBuilder.buildErrorApi(HttpStatus.BAD_REQUEST,
						ExceptionMessageBuilder.getUserMessage(ApiResponseErrorConstant.INVALID_MESSAGE), ex),
				headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, ExceptionMessageBuilder.buildErrorApi(HttpStatus.BAD_REQUEST, null, ex, ex.getBindingResult()),
				headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		return handleExceptionInternal(ex,
				ExceptionMessageBuilder.buildErrorApi(HttpStatus.NOT_FOUND,
						ExceptionMessageBuilder.getUserMessage(ApiResponseErrorConstant.RESOURCE_NOT_FOUND), ex),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		return handleExceptionInternal(ex,
				ExceptionMessageBuilder.buildErrorApi(HttpStatus.BAD_REQUEST,
						ExceptionMessageBuilder.getUserMessage(ApiResponseErrorConstant.OPERATION_NOT_PERMITTED),
						ExceptionUtils.getRootCauseMessage(ex)),
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ NotFoundRequestException.class })
	public ResponseEntity<Object> handleNotFoundRequestException(NotFoundRequestException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				ExceptionMessageBuilder.buildErrorApi(HttpStatus.NOT_FOUND,
						ExceptionMessageBuilder.getUserMessage(ApiResponseErrorConstant.RESOURCE_NOT_FOUND)),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
