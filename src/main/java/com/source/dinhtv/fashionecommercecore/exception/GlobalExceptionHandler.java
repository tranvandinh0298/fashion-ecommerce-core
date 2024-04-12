package com.source.dinhtv.fashionecommercecore.exception;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.ErrorResponse;
import com.source.dinhtv.fashionecommercecore.http.response.PendingResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private Exception ex;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleResourceNotFoundException(Exception ex) {
        BaseResponse<Object> response = new ErrorResponse();
        response.setResultMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<BaseResponse<Object>> handleFileStorageException(Exception ex) {
        BaseResponse<Object> response = new ErrorResponse();
        response.setResultMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<BaseResponse<Object>> handleResourceAlreadyExists(Exception ex) {
        BaseResponse<Object> response = new ErrorResponse();
        response.setResultMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        logger.error("An unexpected ConstraintViolationException occurred");
        BaseResponse<Object> response = new ErrorResponse();
        String message = ex.getConstraintViolations().stream().map(error -> error.getMessage()).toList().get(0);
        response.setResultMessage(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
        logger.error("An unexpected error occurred", ex);
        BaseResponse<Object> response = new PendingResponse();
        if (ex instanceof MethodArgumentNotValidException) {
            List<String> errors = ((MethodArgumentNotValidException) ex).getFieldErrors().stream().map(fieldError -> fieldError.toString()).toList();
            logger.info(errors.toString());
//            return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
            response.setResultMessage("Validate error");
        } else {
            response.setResultMessage(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
