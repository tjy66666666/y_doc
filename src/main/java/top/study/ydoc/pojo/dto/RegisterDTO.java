package top.study.ydoc.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@EqualsAndHashCode
@ToString
@Data
public class RegisterDTO {

    @NotBlank(message = "用户名不可为空哦")
    @Size(max = 18, message = "用户最长为18个字符哦")
    private String username;

    @NotBlank(message = "密码不能为空哦")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$", message = "密码需要8到16位且必须包含数字和字母哦")
    private String password;

    private String avatarUrl;
}
