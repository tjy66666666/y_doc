package top.study.ydoc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tjy
 * @date 2024/5/7
 * @apiNote
 */
@Component
@ConfigurationProperties(prefix = "ydoc.wechat")
@Data
public class WeChatProperties {
    private String appid;
    private String secret;
}
