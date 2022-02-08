package com.sera.exrate.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	// 에러 응답 객체
	private int code;
	private String message;

	public ErrorResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public static ErrorResponse of ( HttpStatus httpStatus, String message ) {
		return new ErrorResponse(httpStatus.value(), message);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	

}
