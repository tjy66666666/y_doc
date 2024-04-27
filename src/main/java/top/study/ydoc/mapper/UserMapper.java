package top.study.ydoc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.study.ydoc.pojo.entity.User;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
