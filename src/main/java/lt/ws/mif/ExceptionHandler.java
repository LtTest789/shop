package lt.ws.mif;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by Romas on 5/19/2017.
 */
@ControllerAdvice
@Component
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenException.class)
    public ResponseEntity<Error> handle(TokenException ex) {
        return new ResponseEntity<>(new Error("Invalid JWT token!"), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handle2(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new Error("Wrong username or password!"), HttpStatus.BAD_REQUEST);
    }
}
