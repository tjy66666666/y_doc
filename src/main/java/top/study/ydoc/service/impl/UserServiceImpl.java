package top.study.ydoc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.study.ydoc.common.config.WeChatProperties;
import top.study.ydoc.common.constant.WeChatConstant;
import top.study.ydoc.common.exception.CustomException;
import top.study.ydoc.common.util.EncryptionUtils;
import top.study.ydoc.common.util.HttpClientUtil;
import top.study.ydoc.mapper.UserMapper;
import top.study.ydoc.pojo.dto.LoginDTO;
import top.study.ydoc.pojo.dto.RegisterDTO;
import top.study.ydoc.pojo.dto.WeChatUserLoginDTO;
import top.study.ydoc.pojo.entity.User;
import top.study.ydoc.service.BaseErrorInfoInterface;
import top.study.ydoc.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;


    /**
     * 用户注册
     *
     * @param registerDTO 注册类
     * @return bool
     */
    @Override
    public boolean register(RegisterDTO registerDTO) {
        // 校验用户名是否重复
        if (!checkUsername(registerDTO.getUsername())) {
            throw new CustomException("用户名重复啦 请换一个试试看～");
        }

        User newUser = new User();
        BeanUtils.copyProperties(registerDTO, newUser);
        try {
            newUser.setPassword(EncryptionUtils.encrypt(registerDTO.getPassword()));
        } catch (Exception e) {
            throw new CustomException("用户注册失败");
        }

        newUser.setCreateTime(LocalDateTime.now());
        newUser.setDelFlag(0);
        boolean saved = this.save(newUser);
        if (!saved) {
            log.error("用户注册失败：{}", registerDTO.getUsername());
            throw new CustomException("用户注册失败");
        }

        log.info("用户注册成功：{}", registerDTO.getUsername());
        return true;

    }

    /**
     * 登陆
     *
     * @param loginDTO 登陆类
     * @return bool
     */
    @Override
    public boolean login(LoginDTO loginDTO) {

        String username = loginDTO.getUsername();
        String password;
        try {
            password = EncryptionUtils.encrypt(loginDTO.getPassword());
        } catch (Exception e) {
            throw new CustomException("登陆失败");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username).eq(User::getPassword, password);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new CustomException("用户名或密码错误！");
        }
        return true;
    }

    /**
     * 微信登陆
     *
     * @param source
     * @return User
     */
    @Override
    public User wxLogin(WeChatUserLoginDTO source) {

        //调用微信接口服务,获取当前微信用户的openid
        String openid = getUserOpenid(source);
        if (openid == null) {
            throw new CustomException("获取微信用户标识码失败");
        }

        //是否为新用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getOpenid, openid).last("limit 1"));
        if (ObjectUtil.isNull(user)) {
            user = new User();
            user.setOpenid(openid);
            userMapper.insert(user);
        }
        return user;
    }

    private String getUserOpenid(WeChatUserLoginDTO source) {
        Map<String, String> map = new HashMap<>(4);
        map.put(WeChatConstant.APPID, weChatProperties.getAppid());
        map.put(WeChatConstant.SECRET, weChatProperties.getSecret());
        map.put(WeChatConstant.JS_CODE, source.getCode());
        map.put(WeChatConstant.GRANT_TYPE, WeChatConstant.AUTHORIZATION_CODE);

        //发送请求
        String result = HttpClientUtil.doGet(WeChatConstant.WX_LOGIN, map);

        //解析结果
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.getString(WeChatConstant.OPEN_ID);
        return openid;
    }


    /**
     * 校验用户名是否重复
     *
     * @param username 用户名
     * @return bool
     */
    private boolean checkUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return userMapper.selectCount(queryWrapper) == 0;
    }
}
