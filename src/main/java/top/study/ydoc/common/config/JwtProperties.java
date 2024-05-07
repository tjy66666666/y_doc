package top.study.ydoc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tjy
 * @date 2024/5/6
 * @description
 */
@Component
@ConfigurationProperties(prefix = "ydoc.jwt")
@Data
public class JwtProperties {

    /**
     * 管理端用户生成jwt令牌配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * 用户端用户生成jwt令牌配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}
