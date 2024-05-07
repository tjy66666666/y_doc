package top.study.ydoc.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.study.ydoc.common.result.Result;
import top.study.ydoc.pojo.dto.NoteSearchDTO;
import top.study.ydoc.pojo.vo.NoteVO;
import top.study.ydoc.service.NoteService;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    /**
     * 根据关键词进行笔记查询
     * @param searchDTO 查询参数类
     * @return 查询结果 @class:NoteVO
     */
    @GetMapping("/page")
    public Result<PageInfo<NoteVO>> queryPage(@RequestBody NoteSearchDTO searchDTO) {
        PageInfo<NoteVO> noteVoPageInfo = noteService.queryPage(searchDTO);
        return Result.success(noteVoPageInfo);
    }

}
