package com.jxufe.mapper;

import com.jxufe.dto.*;
import com.jxufe.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     */

    void insert(User user);

    /**
     * 根据用户名和密码查询用户
     * @param userLoginDTO
     * @return
     */
    @Select("select * from users where name = #{userName} and password = #{password}")
    User getByUsernameAndPassword(UserLoginDTO userLoginDTO);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    @Select("select * from users where id = #{userId}")
    User getByUserId(Long userId);

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
    @Insert("insert into user_favorites(favorite_id, user_id) VALUES (#{favoriteId}, #{userId})")
    void addFavorite(FavoriteDTO favoriteDTO);

    /**
     * 获取用户收藏
     * @return
     */
    @Select("select * from user_favorites a " +
            "left join himu_music.musics m on m.id = a.favorite_id where a.user_id = #{userId}")
    List<MusicDTO> getFavorite(@Param("userId") Long userId);

    /**
     * 添加用户最近播放
     * @param recentPlayDTO
     */
    @Insert("insert into user_recent_play(user_id, recent_play_id) VALUES (#{userId},#{RecentPlayId})")
    void addRecentPlay(RecentPlayDTO recentPlayDTO);

    /**
     * 获取用户最近播放
     * @return
     */
    @Select("select * from user_recent_play a " +
            "left join himu_music.musics m on m.id = a.recent_play_id where a.user_id = #{userId}")
    List<MusicDTO> getRecentPlay(@Param("userId") Long userId);
}
