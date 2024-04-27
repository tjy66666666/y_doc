package top.study.ydoc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.study.ydoc.mapper.NoteMapper;
import top.study.ydoc.pojo.dto.NoteSearchDTO;
import top.study.ydoc.pojo.entity.Note;
import top.study.ydoc.pojo.vo.NoteVO;
import top.study.ydoc.service.NoteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tjy
 * @date 2024/4/14
 * @apiNote
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    /**
     * 分页查询个人笔记
     *
     * @param searchDTO 查询参数类
     * @return Result<PageInfo < NoteVO>
     */
    @Override
    public PageInfo<NoteVO> queryPage(NoteSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper<>();
        String keyword = searchDTO.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like(Note::getTitle, keyword)
                    .or().like(Note::getContent, keyword)
                    .or().like(Note::getNoteTime, keyword)
                    .or().like(Note::getLocation, keyword);
        }

        List<Note> notes = noteMapper.selectList(queryWrapper);

        // 将Note对象转换为NoteVo对象
        return new PageInfo<>(noteEntity2noteVo(notes));
    }


    private static List<NoteVO> noteEntity2noteVo(List<Note> notes) {
        List<NoteVO> noteVos = new ArrayList<>();
        for (Note note : notes) {
            NoteVO noteVO = new NoteVO();
            BeanUtils.copyProperties(note, noteVO);
            noteVos.add(noteVO);
        }
        return noteVos;
    }
}
