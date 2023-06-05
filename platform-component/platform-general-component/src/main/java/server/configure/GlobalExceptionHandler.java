package server.configure;

import com.cy.platform.generation.server.common.PublicResult;
import com.cy.platform.generation.server.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 *
 * @author 56807
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public PublicResult<?> handleServiceException(ServiceException e) {
        return PublicResult.failure(e.getMessage());
    }

    /**
     * 全部异常
     */
    @ExceptionHandler(Exception.class)
    public PublicResult<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return PublicResult.failure("操作失败");
    }
}
