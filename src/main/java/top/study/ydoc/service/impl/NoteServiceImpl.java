package top.study.ydoc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.study.ydoc.common.config.ProjectProperties;
import top.study.ydoc.common.util.MinioUtils;
import top.study.ydoc.mapper.NoteMapper;
import top.study.ydoc.pojo.dto.NoteAddDTO;
import top.study.ydoc.pojo.dto.NoteSearchDTO;
import top.study.ydoc.pojo.dto.UploadFilesDTO;
import top.study.ydoc.pojo.entity.Note;
import top.study.ydoc.pojo.vo.NoteVO;
import top.study.ydoc.service.NoteService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tjy
 * @date 2024/4/14
 * @apiNote
 */
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;
    @Resource
    private ProjectProperties projectProperties;

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

    /**
     * 笔记上传
     *
     * @param noteAddDTO 笔记上传所需相关参数类
     * @return 是否上传成功
     */
    @Override
    public boolean noteAdd(NoteAddDTO noteAddDTO) {

        return false;
    }

    /**
     * 上传笔记关联文件列表
     *
     * @param uploadFilesDTO 关联文件列表
     * @return 是否上传成功
     */
    @Override
    public boolean filesUpload(UploadFilesDTO uploadFilesDTO) {
        if (uploadFilesDTO != null) {
            List<MultipartFile> files = uploadFilesDTO.getFiles();
            return upload2Minio(files);
        }
        return false;
    }

    private boolean upload2Minio(List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                MinioUtils.putObject(projectProperties.minioBucketOfImg,
                        file.getOriginalFilename(),
                        file.getInputStream());
            }
        } catch (Exception e) {
            log.error("文件上传失败！");
            return false;
        }
        return true;

    }


    private static List<NoteVO> noteEntity2noteVo(List<Note> notes) {
        List<NoteVO> noteVos = new ArrayList<>();
        notes.forEach(note -> {
            NoteVO noteVO = new NoteVO();
            BeanUtils.copyProperties(note, noteVO);
            noteVos.add(noteVO);
        });
        return noteVos;
    }
}
