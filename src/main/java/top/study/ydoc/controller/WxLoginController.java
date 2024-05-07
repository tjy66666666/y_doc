package top.study.ydoc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.study.ydoc.common.config.JwtProperties;
import top.study.ydoc.common.constant.JwtClaimsConstant;
import top.study.ydoc.common.result.Result;
import top.study.ydoc.common.util.JwtUtil;
import top.study.ydoc.pojo.dto.WeChatUserLoginDTO;
import top.study.ydoc.pojo.entity.User;
import top.study.ydoc.pojo.vo.WeChatUserLoginVO;
import top.study.ydoc.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tjy
 * @date 2024/5/6
 * @description 微信小程序登陆接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class WxLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 微信登陆
     * 官方接口文档：
     * <a href = "https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html"
     */
    @PostMapping("/wx/login")
    public Result<WeChatUserLoginVO> login(@RequestBody WeChatUserLoginDTO weChatUserLoginDTO) {
        log.info("微信用户登陆：{}", weChatUserLoginDTO.getCode());

        // 微信登陆
        User user = userService.wxLogin(weChatUserLoginDTO);

        Map<String ,Object> claims = new HashMap<>(2);
        claims.put(JwtClaimsConstant.USER_ID,user.getId());

        // 获取用户token
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        WeChatUserLoginVO userLoginVO = WeChatUserLoginVO.builder().id(user.getId()).openid(user.getOpenid()).token(token).build();
        return Result.success(userLoginVO);
    }

}
