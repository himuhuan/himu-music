package com.jxufe.controller;

import com.jxufe.dto.MusicDTO;
import com.jxufe.dto.RecentPlayDTO;
import com.jxufe.mapper.UserMapper;
import com.jxufe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.OrientationRequested;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/recent_play")
public class RecentPlayController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户最近播放
     * @param recentPlayDTO
     */
    @PostMapping
    public void addRecentPlay(@RequestBody RecentPlayDTO recentPlayDTO) {
        userService.addRecentPlay(recentPlayDTO);
    }

    /**
     * 获取用户最近播放
     * @return
     */
    @GetMapping("/user/{userId}")
    public List<MusicDTO> getRecentPlay(@PathVariable Long userId) {
        return userService.getRecentPlay(userId);
    }
}
