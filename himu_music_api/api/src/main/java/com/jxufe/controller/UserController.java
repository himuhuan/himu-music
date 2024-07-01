package com.jxufe.controller;

import com.jxufe.dto.FavoriteDTO;
import com.jxufe.dto.UserDTO;
import com.jxufe.dto.UserLoginDTO;
import com.jxufe.entity.User;
import com.jxufe.service.UserService;
import com.jxufe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    @PostMapping
    public Long register(@RequestBody UserDTO userDTO) {
        Long id = userService.register(userDTO);
        return id;
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/authenticate")
    public Long login(@RequestBody UserLoginDTO userLoginDTO) {

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        if(user != null) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("name",user.getName());
            claims.put("password",user.getPassword());
            claims.put("avatarBase64",user.getAvatarBase64());
            return user.getId();
        }
        return 0L;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return user;
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @PutMapping
    public void update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }
}
