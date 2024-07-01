package com.jxufe.mapper;

import com.jxufe.dto.MusicDTO;
import com.jxufe.dto.UploadMusicRequestDTO;
import com.jxufe.entity.Music;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MusicMapper {

    /**
     * 音乐搜索
     * @param query
     * @return
     */
    @Select("select * from himu_music.musics where title like CONCAT('%', #{query}, '%')")
    List<Music> musicSearch(String query);

    @Select("select * from himu_music.musics where id = #{musicId}")
    Music getById(Long musicId);

    /**
     * 添加音乐
     * @param uploadMusicRequestDTO
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into himu_music.musics(title, source ,firstPerformer, albumPictureBase64, duration, issuerId) VALUES " +
            "(#{title}, #{source}, #{firstPerformer},#{albumPictureBase64},#{duration},#{issuerId})")
    void addMusic(Music music);

    @Update("update himu_music.musics set source = #{source} where id = #{musicId}")
    void updateSource(@Param("source") String source, @Param("musicId") Long musicId);
}
