package org.learnify.com.counsellor_portal_app.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
public class ExceptionDto {
    private String message;
    private String details;
    private HttpStatus httpStatus;
    private HttpStatusCode httpStatusCode;
}
