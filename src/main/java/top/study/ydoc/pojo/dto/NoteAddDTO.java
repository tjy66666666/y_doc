package top.study.ydoc.pojo.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author tjy
 * @date 2024/5/5
 * @apiNote
 */
@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteAddDTO {
    /**
     * 笔记内容
     */
    @Length(max = 8000, message = "字符串长度不能超过8000")
    private String content;
    /**
     * 笔记关联文件的路径列表
     */
    @Size(max = 50, message = "文件列表大小不能超过50")
    private List<String> filesPaths;
}
