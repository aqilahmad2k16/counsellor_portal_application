package org.learnify.com.counsellor_portal_app.exceptions;

import org.learnify.com.counsellor_portal_app.dtos.responseDtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CounsellorNotFoundException.class)
    public ResponseEntity<ExceptionDto> counsellorNotFoundException(CounsellorNotFoundException ex, WebRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDto.setHttpStatusCode(HttpStatus.valueOf(404));
        exceptionDto.setDetails(request.getDescription(false));
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DashboardNotFoundException.class)
    public ResponseEntity<ExceptionDto> dashboardNotFoundException(DashboardNotFoundException ex, WebRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDto.setHttpStatusCode(HttpStatus.valueOf(404));
        exceptionDto.setDetails(request.getDescription(false));
        return  new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    
}
