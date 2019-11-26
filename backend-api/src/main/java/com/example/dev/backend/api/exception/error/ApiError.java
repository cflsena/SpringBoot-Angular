package com.example.dev.backend.api.exception.error;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ApiError {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime localDatetime;
	private String userMessage;
	private String debugMessage;
	private List<ApiSubError> apiSubErrorList;

	private ApiError() {
		this.setLocalDatetime(LocalDateTime.now());
	}

	public ApiError(HttpStatus httpStatus) {
		this();
		this.status = httpStatus;
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.debugMessage = ex.getCause().toString();
	}

	public ApiError(HttpStatus status, String userMessage, Throwable ex) {
		this();
		this.status = status;
		this.userMessage = userMessage;
		this.debugMessage = ex.getCause().toString();
	}
	
	public ApiError(HttpStatus status, String userMessage) {
		this();
		this.status = status;
		this.userMessage = userMessage;
	}

	public ApiError(HttpStatus status, Throwable ex, List<ApiSubError> apiSubErrorList) {
		this();
		this.status = status;
		this.debugMessage = ExceptionUtils.getRootCauseMessage(ex);
		this.setApiSubErrorList(apiSubErrorList);
	}
}
