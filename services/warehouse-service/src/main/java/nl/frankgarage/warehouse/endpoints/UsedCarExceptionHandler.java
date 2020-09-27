package nl.frankgarage.warehouse.endpoints;

import nl.frankgarage.warehouse.exceptions.ExceptionResponse;
import nl.frankgarage.warehouse.exceptions.UserHeaderNotFoundException;
import nl.frankgarage.warehouse.exceptions.UsedCarNotFoundException;
import nl.frankgarage.warehouse.exceptions.WarehouseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UsedCarExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsedCarNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(UsedCarNotFoundException usedCarNotFoundException) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                                                     .message(usedCarNotFoundException.getMessage())
                                                     .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                                                     .timestamp(LocalDateTime.now())
                                                     .build(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WarehouseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(WarehouseNotFoundException warehouseNotFoundException) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                                                     .message(warehouseNotFoundException.getMessage())
                                                     .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                                                     .timestamp(LocalDateTime.now())
                                                     .build(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserHeaderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUnAuthorisedException(UserHeaderNotFoundException userHeaderNotFoundException) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                                                     .message(userHeaderNotFoundException.getMessage())
                                                     .status(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                                                     .timestamp(LocalDateTime.now())
                                                     .build(), HttpStatus.UNAUTHORIZED);
    }
}
