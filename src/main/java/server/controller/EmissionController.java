package server.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * The Emission controller
 */
@RestController
public class EmissionController {
    /**
     * Handle method argument type mismatch response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    //Handles bad requests
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
