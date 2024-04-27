package top.study.ydoc.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.study.ydoc.common.result.Result;

import java.util.List;


/**
 * 全局异常处理类
 *
 * @author tjy
 * @date 2024/04/14
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param e BizException
     * @return Result
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result<?> bizExceptionHandler(CustomException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return Result.fail(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param e NullPointerException
     * @return Result
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result<?> exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常！原因是:{}", e.getMessage());
        return Result.fail(ExceptionEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理运行时异常
     *
     * @param e RuntimeException
     * @return Result
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result<?> exceptionHandler(RuntimeException e) {
        log.error("未知异常！原因是:{}", e.getMessage());
        return Result.fail(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数错误！原因是:{}", e.getMessage());
        List<ObjectError> errors = e.getAllErrors();
        if (!errors.isEmpty()) {
            String defaultMessage = errors.get(0).getDefaultMessage();
            return Result.fail(ExceptionEnum.PARAMS_ERROR.getResultCode(), defaultMessage);
        }
        return Result.fail(ExceptionEnum.PARAMS_ERROR);
    }

}
