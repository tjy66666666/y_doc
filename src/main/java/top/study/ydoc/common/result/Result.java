package top.study.ydoc.common.result;

import lombok.Builder;
import top.study.ydoc.service.BaseErrorInfoInterface;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@Builder
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return Result.<T>builder().code("200").build();
    }

    public static <T> Result<T> success(String msg) {
        return Result.<T>builder().code("200").msg(msg).build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder().code("200").data(data).build();
    }

    public static <T> Result<T> success(String msg, T data) {
        return Result.<T>builder().code("200").msg(msg).data(data).build();
    }

    public static <T> Result<T> fail() {
        return Result.<T>builder().code(ResultEnum.SYSTEM_ERROR.getCode()).build();
    }

    public static <T> Result<T> fail(BaseErrorInfoInterface errorInfo) {
        return Result.<T>builder().code(errorInfo.getResultCode()).msg(errorInfo.getResultMsg()).build();
    }

    public static <T> Result<T> fail(String code, String msg) {
        return Result.<T>builder().code(code).msg(msg).build();
    }

    public static <T> Result<T> fail(String msg) {
        return Result.<T>builder().code(ResultEnum.SYSTEM_ERROR.getCode()).msg(msg).build();
    }

    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return Result.<T>builder().code(resultEnum.getCode()).msg(resultEnum.getMsg()).build();
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
