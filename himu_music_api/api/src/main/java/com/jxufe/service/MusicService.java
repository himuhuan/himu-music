package com.jxufe.service;

import com.jxufe.dto.MusicDTO;
import com.jxufe.dto.UploadMusicRequestDTO;
import com.jxufe.entity.Music;

import java.util.List;

public interface MusicService {
    /**
     * 音乐搜索
     * @param query
     * @return
     */
    List<Music> musicSearch(String query);

    /**
     * 添加音乐
     * @param uploadMusicRequestDTO
     */
    Long addMusic(UploadMusicRequestDTO uploadMusicRequestDTO);


    void update(String source,Long musicId);

    Music getById(Long musicId);
}
