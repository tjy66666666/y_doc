package top.study.ydoc.service;

import top.study.ydoc.pojo.dto.LoginDTO;
import top.study.ydoc.pojo.dto.RegisterDTO;
import top.study.ydoc.pojo.dto.WeChatUserLoginDTO;
import top.study.ydoc.pojo.entity.User;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param registerDTO 注册类
     * @return bool
     */
    boolean register(RegisterDTO registerDTO);

    /**
     * 登陆
     *
     * @param loginDTO 登陆类
     * @return bool
     */
    boolean login(LoginDTO loginDTO);

    /**
     * 微信登陆
     * @param weChatUserLoginDTO
     * @return
     */
    User wxLogin(WeChatUserLoginDTO weChatUserLoginDTO);
}
