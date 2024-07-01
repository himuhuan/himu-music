package com.jxufe.service;

import com.jxufe.dto.*;
import com.jxufe.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    Long register(UserDTO userDTO);

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User getById(Long userId);

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    void update(UserDTO userDTO);

    /**
     * 添加用户收藏
     * @param favoriteDTO
     */
    void addFavorite(FavoriteDTO favoriteDTO);

    /**
     * 获取用户收藏
     * @return
     */
    List<MusicDTO> getFavorite(Long userId);

    /**
     * 添加用户最近播放
     * @param recentPlayDTO
     */
    void addRecentPlay(RecentPlayDTO recentPlayDTO);

    /**
     * 获取用户最近播放
     * @return
     */
    List<MusicDTO> getRecentPlay(Long userId);
}
