package top.study.ydoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.study.ydoc.common.result.Result;
import top.study.ydoc.pojo.dto.FileTransferDTO;
import top.study.ydoc.service.ToolService;

/**
 * @author tjy
 * @date 2024/4/23
 * @apiNote
 */
@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @PostMapping("/convert")
    public Result<?> fileConvert(@RequestBody FileTransferDTO fileTransferDTO){
        return toolService.fileConvert(fileTransferDTO);
    }
}
