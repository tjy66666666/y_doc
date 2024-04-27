package top.study.ydoc.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.study.ydoc.common.result.Result;
import top.study.ydoc.pojo.dto.FileTransferDTO;

/**
 * @author tjy
 * @date 2024/4/23
 * @apiNote
 */
@Component
public interface ToolService {

    /**
     * 文件转换 根据文件后缀名转换输出内容
     * @param fileTransferDTO 待转换文件描述参数
     * @return Result
     */
    Result<?> fileConvert(FileTransferDTO fileTransferDTO);
}
