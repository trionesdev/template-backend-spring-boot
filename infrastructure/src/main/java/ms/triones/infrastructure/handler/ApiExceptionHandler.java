package ms.triones.infrastructure.handler;

import com.moensun.commons.exception.ErrorResponse;
import com.moensun.commons.exception.MSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MSException.class})
    public ErrorResponse msEx(HttpServletResponse response, MSException ex) {
        if (log.isErrorEnabled()) {
            log.error(ex.getMessage(), ex);
        }
        response.setStatus(460);
        return ErrorResponse.builder().code(ex.getCode()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ErrorResponse ex(HttpServletResponse response, Exception ex) {
        if (log.isErrorEnabled()) {
            log.error(ex.getMessage(), ex);
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ErrorResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.name()).message("系统异常").build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ErrorResponse handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {
        if (log.isErrorEnabled()) {
            log.error(ex.getMessage(), ex);
        }
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String message = error.getDefaultMessage();

        response.setStatus(460);
        return ErrorResponse.builder().code("ARGUMENT_VALIDATION_ERROR").message(message).build();
    }

    @ExceptionHandler(value = {BindException.class})
    public ErrorResponse handleBindException(HttpServletResponse response, BindException ex) {
        if (log.isErrorEnabled()) {
            log.error(ex.getMessage(), ex);
        }
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String message = error.getDefaultMessage();

        response.setStatus(460);
        return ErrorResponse.builder().code("BIND_VALIDATION_ERROR").message(message).build();
    }

}
