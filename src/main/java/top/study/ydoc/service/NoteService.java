package top.study.ydoc.service;

import com.github.pagehelper.PageInfo;
import top.study.ydoc.pojo.dto.NoteSearchDTO;
import top.study.ydoc.pojo.vo.NoteVO;


/**
 * @author tjy
 * @date 2024/04/23
 */

public interface NoteService {

    /**
     * 分页查询个人笔记
     *
     * @param searchDTO 查询参数类
     * @return PageInfo < NoteVO>
     */
    PageInfo<NoteVO> queryPage(NoteSearchDTO searchDTO);
}
