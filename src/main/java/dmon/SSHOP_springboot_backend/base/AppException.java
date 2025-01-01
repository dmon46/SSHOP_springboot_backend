package dmon.SSHOP_springboot_backend.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class AppException extends RuntimeException {
    public AppException(ExceptionCode exception)  {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.message = exception.getMessage();
        this.status = exception.getStatus();
    }

    private int code;
    private String message;
    private HttpStatusCode status;
}
