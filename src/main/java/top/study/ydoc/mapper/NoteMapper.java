package top.study.ydoc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.study.ydoc.pojo.entity.Note;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {
}
