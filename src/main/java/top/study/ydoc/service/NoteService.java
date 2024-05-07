package top.study.ydoc.service;

import com.github.pagehelper.PageInfo;
import top.study.ydoc.pojo.dto.NoteAddDTO;
import top.study.ydoc.pojo.dto.NoteSearchDTO;
import top.study.ydoc.pojo.dto.UploadFilesDTO;
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

    /**
     * 笔记上传
     *
     * @param noteAddDTO 笔记上传所需相关参数类
     * @return 是否上传成功
     */
    boolean noteAdd(NoteAddDTO noteAddDTO);

    /**
     * 上传笔记关联文件列表
     *
     * @param uploadFilesDTO 关联文件列表
     * @return 是否上传成功
     */
    boolean filesUpload(UploadFilesDTO uploadFilesDTO);
}
