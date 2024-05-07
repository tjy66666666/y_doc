package top.study.ydoc.common.result;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
public enum ResultEnum {

    /**
     * 错误码规定：
     * 系统错误相关4000-5999
     * 用户/权限相关 6000~6999
     * 工具相关 7000~7999
     * 笔记相关 8000~8999
     */


    // 用户/权限相关 6000~6999
    USERNAME_REPEAT("6001", "用户名重复啦 请换一个试试看～"),

    // 工具相关 7000~7999
    TOOL_FILE_OR_FILENAME_ERROR("7000", "文件或文件名有误"),
    TOOL_FILE_MATCHED_AFTER_TRANSFER("7001", "上传文件与待转换文件类型相同"),
    TOOL_FILE_TRANSFER_ERROR("7002", "文件转换异常！"),

    // 笔记相关
    NOTE_UPLOAD_ERROR("8000", "笔记上传异常！"),

    // 系统错误相关4000-5999
    /**
     * 请求参数错误
     */
    REQUEST_PARAMS_ERROR("4001", "请求参数错误！"),
    /**
     * 服务器异常
     */
    SYSTEM_ERROR("5001", "服务器异常！");


    private String code;
    private String msg;

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

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
