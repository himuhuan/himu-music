package com.jxufe.service.impl;

import com.jxufe.dto.MusicDTO;
import com.jxufe.dto.UploadMusicRequestDTO;
import com.jxufe.entity.Music;
import com.jxufe.mapper.MusicMapper;
import com.jxufe.service.MusicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;
    /**
     * 音乐搜索
     * @param query
     * @return
     */
    public List<Music> musicSearch(String query) {
        return musicMapper.musicSearch(query);
    }

    /**
     * 添加音乐
     * @param uploadMusicRequestDTO
     */
    public Long addMusic(UploadMusicRequestDTO uploadMusicRequestDTO) {
        Music music = new Music();
        BeanUtils.copyProperties(uploadMusicRequestDTO,music);
        musicMapper.addMusic(music);
        return music.getId();
    }


    public void update(String source, Long musicId) {
        musicMapper.updateSource(source, musicId);
    }

    public Music getById(Long musicId) {
        return musicMapper.getById(musicId);
    }
}
