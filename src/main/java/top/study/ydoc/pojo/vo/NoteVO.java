package top.study.ydoc.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author tjy
 * @date 2024/4/14
 * @apiNote
 */
@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteVO {

    /**
     * 主键雪花ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 笔记内容
     */
    private String content;
    /**
     * 笔记时间
     */
    private LocalDateTime noteTime;
    /**
     * 是否提醒
     */
    private String alertFlag;
    /**
     * 提醒方式 0 短信 1 邮件 2 电话提醒 3 通知推送 4 待定 默认3
     */
    private Integer alertWay;
    /**
     * 位置
     */
    private String location;
    /**
     * 笔记状态 0 草稿 1 编辑完成 2 未提醒 3 已提醒
     */
    private Integer status;
}
