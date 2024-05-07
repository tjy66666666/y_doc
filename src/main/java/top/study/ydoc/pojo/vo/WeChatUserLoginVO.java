package top.study.ydoc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tjy
 * @date 2024/5/7
 * @apiNote
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeChatUserLoginVO implements Serializable {

    private Long id;
    private String openid;
    private String token;

}
