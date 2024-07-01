package com.jxufe.service.impl;

import com.jxufe.dto.*;
import com.jxufe.entity.User;
import com.jxufe.mapper.UserMapper;
import com.jxufe.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    public Long register(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        userMapper.insert(user);

        return user.getId();
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        User user = userMapper.getByUsernameAndPassword(userLoginDTO);

        return user;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public User getById(Long userId) {
        User user = userMapper.getByUserId(userId);
        return user;
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    public void update(UserDTO userDTO) {
        userMapper.update(userDTO);
    }

    /**
     * 添加用户收藏
     * @param favoriteDTO
     */
    public void addFavorite(FavoriteDTO favoriteDTO) {
        userMapper.addFavorite(favoriteDTO);
    }

    /**
     * 获取用户收藏
     * @return
     */
    public List<MusicDTO> getFavorite(Long userId) {
        return userMapper.getFavorite(userId);
    }

    /**
     * 添加用户最近播放
     * @param recentPlayDTO
     */
    public void addRecentPlay(RecentPlayDTO recentPlayDTO) {
        userMapper.addRecentPlay(recentPlayDTO);
    }

    /**
     * 获取用户最近播放
     * @return
     */
    public List<MusicDTO> getRecentPlay(Long userId) {
        return userMapper.getRecentPlay(userId);
    }
}
