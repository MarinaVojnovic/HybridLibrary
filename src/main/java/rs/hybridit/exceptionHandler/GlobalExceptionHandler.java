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
		HttpHeaders headers = new HttpHeaders();
		return handleInvalidIdException(ex, headers, HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<?> handleInvalidIdException(InvalidIdException ex, HttpHeaders headers, HttpStatus status,
		WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		return handleExceptionInternal(ex, errors, headers, status, request);
	}

	protected ResponseEntity<?> handleExceptionInternal(Exception ex, List<String> body, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}

}
