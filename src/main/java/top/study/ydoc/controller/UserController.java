package top.study.ydoc.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.study.ydoc.common.result.Result;
import top.study.ydoc.pojo.dto.LoginDTO;
import top.study.ydoc.pojo.dto.RegisterDTO;
import top.study.ydoc.service.UserService;

/**
 * @author tjy
 * @date 2024/4/13
 * @apiNote
 */
@Validated
@RestController
@RequestMapping("/user")
// TODO 整合token校验机制
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody RegisterDTO registerDTO) {

        boolean success = userService.register(registerDTO);
        return success ? Result.success("注册成功！") : Result.fail("注册失败！");
    }

    @PostMapping("/login")
    public Result<?> register(@Valid @RequestBody LoginDTO loginDTO) {

        boolean success = userService.login(loginDTO);
        return success ? Result.success("登陆成功！") : Result.fail("登陆失败！");
    }
}
