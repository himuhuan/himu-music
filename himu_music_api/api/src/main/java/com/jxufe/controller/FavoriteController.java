package com.jxufe.controller;

import com.jxufe.dto.FavoriteDTO;
import com.jxufe.dto.MusicDTO;
import com.jxufe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@Slf4j
public class FavoriteController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户收藏
     * @param favoriteDTO
     */
    @PostMapping
    public void favorite(@RequestBody FavoriteDTO favoriteDTO) {
        userService.addFavorite(favoriteDTO);
    }

    /**
     * 获取用户收藏
     * @return
     */
    @GetMapping("/user/{userId}")
    public List<MusicDTO> getFavorite(@PathVariable Long userId) {
        return userService.getFavorite(userId);
    }
}
