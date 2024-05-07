package top.study.ydoc.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tjy
 * @date 2024/5/7
 * @description 微信登陆
 */
@Data
public class WeChatUserLoginDTO implements Serializable {
    /**
     * 授权码
     */
    private String code;
}
