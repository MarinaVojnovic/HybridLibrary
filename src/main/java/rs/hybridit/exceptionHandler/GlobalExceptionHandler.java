package rs.hybridit.exceptionHandler;


import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import rs.hybridit.exception.InvalidIdException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({InvalidIdException.class})
	public final ResponseEntity<?> handleException(InvalidIdException ex, WebRequest request) {
		return handleInvalidIdException(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<?> handleInvalidIdException(InvalidIdException ex, HttpHeaders headers, HttpStatus status,
		WebRequest request) {
		return handleExceptionInternal(ex, Collections.singletonList(ex.getMessage()), headers, status, request);
	}

	protected ResponseEntity<?> handleExceptionInternal(Exception ex, List<String> body, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}

}
