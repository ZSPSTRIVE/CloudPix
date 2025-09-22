package com.yupi.yupicture.infrastructure.exception;

import com.yupi.yupicture.infrastructure.common.BaseResponse;
import com.yupi.yupicture.infrastructure.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException: code={}, message={}", e.getCode(), e.getMessage(), e);

        Throwable cause = e.getCause();
        if (cause != null) {
            log.error("底层异常信息: class={}, message={}", cause.getClass().getName(), cause.getMessage(), cause);
        }

        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
