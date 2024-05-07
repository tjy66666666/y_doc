package top.study.ydoc.pojo.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author tjy
 * @date 2024/5/5
 * @apiNote
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UploadFilesDTO {
    /**
     * 待上传文件
     */
    private List<MultipartFile> files;
}
