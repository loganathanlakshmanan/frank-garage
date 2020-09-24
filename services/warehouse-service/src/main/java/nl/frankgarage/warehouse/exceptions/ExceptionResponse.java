package nl.frankgarage.warehouse.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder()
@AllArgsConstructor
@Data
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String status;
    private String message;
}
