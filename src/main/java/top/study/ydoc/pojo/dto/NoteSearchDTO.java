package top.study.ydoc.pojo.dto;

import lombok.*;

/**
 * @author tjy
 * @date 2024/4/14
 * @apiNote
 */
@EqualsAndHashCode
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteSearchDTO extends PageParams {
    private String keyword;
}
