package fi.kajstrom.repmaxtracker.resources;

import fi.kajstrom.repmaxtracker.domain.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResource resource = new ErrorResource(1, ex.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, resource, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        ErrorResource resource = new ErrorResource(2, ex.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, resource, headers, HttpStatus.BAD_REQUEST, request);
    }
}
