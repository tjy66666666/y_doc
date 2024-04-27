package top.study.ydoc.pojo.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@EqualsAndHashCode
@ToString
@Data
public class LoginDTO {
    @NotBlank(message = "用户名不可为空哦")
    private String username;
    private String password;
}
