package top.study.ydoc.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@TableName("t_user")
@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class User {
    /**
     * 主键雪花ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 微信用户唯一标识
     */
    private String openid;
    /**
     * 手机号（小程序若为企业资质则有权限获取得到用户手机号）
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idNumber;
    private String username;
    private String password;

    /**
     * 头像地址
     */
    private Integer avatarUrl;
    /**
     * 删除标识
     */
    @TableLogic(delval = "1")
    private Integer delFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public User(Integer delFlag, LocalDateTime createTime) {
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.updateTime = LocalDateTime.now();
    }

    public User(Long id, String username, String password, Integer avatarUrl, Integer delFlag, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.updateTime = LocalDateTime.now();
    }

    public User() {
        this.updateTime = LocalDateTime.now();
    }
}
