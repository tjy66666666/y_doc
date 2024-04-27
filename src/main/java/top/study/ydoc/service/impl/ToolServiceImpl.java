package top.study.ydoc.service.impl;

import org.springframework.stereotype.Service;
import top.study.ydoc.common.result.Result;
import top.study.ydoc.common.result.ResultEnum;
import top.study.ydoc.common.util.FileUtils;
import top.study.ydoc.pojo.dto.FileTransferDTO;
import top.study.ydoc.service.ToolService;

/**
 * @author tjy
 * @date 2024/4/23
 * @apiNote
 */
@Service
public class ToolServiceImpl implements ToolService {

    /**
     * 文件转换 根据文件后缀名转换输出内容
     *
     * @param fileTransferDTO 待转换文件描述参数
     * @return Result
     */
    @Override
    public Result<?> fileConvert(FileTransferDTO fileTransferDTO) {

        // 检查文件是否存在以及文件名是否为空
        if (fileTransferDTO.getFile() == null || fileTransferDTO.getFile().getOriginalFilename() == null) {
            return Result.fail(ResultEnum.TOOL_FILE_OR_FILENAME_ERROR);
        }

        // 获取文件名
        String fileName = fileTransferDTO.getFile().getOriginalFilename();

        // 获取文件后缀
        String fileExtension = FileUtils.getFileExtension(fileName);

        // 检查文件后缀是否与指定的后缀相同
        if (fileExtension != null && fileExtension.equalsIgnoreCase(fileTransferDTO.getAfterTransfer())) {
            return Result.fail(ResultEnum.TOOL_FILE_MATCHED_AFTER_TRANSFER);
        }

        boolean success = FileUtils.fileConvert(fileTransferDTO.getFile(),fileTransferDTO.getAfterTransfer());
        return success ? Result.success():Result.fail(ResultEnum.TOOL_FILE_TRANSFER_ERROR);
    }
}
