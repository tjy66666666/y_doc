package top.study.ydoc.pojo.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tjy
 * @date 2024/4/23
 * @apiNote
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class FileTransferDTO {

    /**
     * 转换后文件格式 .doc .docx .pdf .png...
     */
    private String afterTransfer;

    /**
     * 待转换文件
     */
    private MultipartFile file;
}
