package top.study.ydoc.service;


/**
 * @author tjy
 * @date 2024/04/14
 */

public interface BaseErrorInfoInterface {

    /**
     *  错误码
     * @return
     */
    String getResultCode();

    /**
     * 错误描述
     * @return
     */
    String getResultMsg();
}
