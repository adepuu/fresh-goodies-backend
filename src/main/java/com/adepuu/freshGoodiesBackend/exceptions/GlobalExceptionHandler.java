package com.adepuu.freshGoodiesBackend.exceptions;

import com.adepuu.freshGoodiesBackend.responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.net.UnknownHostException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response<String>> handleAllExceptions(Exception ex) {

		log.error(ex.getMessage(), ex);

		if (ex.getCause() instanceof UnknownHostException) {
			return Response.failedResponse(HttpStatus.NOT_FOUND.value(),
							ex.getLocalizedMessage());
		}

		return Response.failedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						"We are unable to process your request at this time, please try again later.", ex.getMessage());
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Response<Object>> handleApplicationException(ApplicationException ex) {
		return Response.failedResponse(ex.getHttpStatus().value(), ex.getMessage());
	}

}
