package com.jxufe.controller;

import com.jxufe.dto.MusicDTO;
import com.jxufe.dto.UploadMusicRequestDTO;
import com.jxufe.entity.Music;
import com.jxufe.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping
    public Long uploadMusicInfo(@RequestBody UploadMusicRequestDTO uploadMusicRequestDTO) {
        uploadMusicRequestDTO.setSource("");
        return musicService.addMusic(uploadMusicRequestDTO);
    }

    /**
     * 文件上传
     * @throws IOException
     */
    @PostMapping( "/{musicId}/upload")
    public void uploadMusic(@RequestBody MultipartFile file, @PathVariable Long musicId) throws IOException {
        // MultipartFile file = uploadMusicRequestDTO.getMusicFile();

        //获取原始的文件名
        String originalFilename = file.getOriginalFilename();

        //构造唯一的文件名 - uuid
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;

        //将文件存储在服务器的磁盘目录下
        file.transferTo(new File("D:\\HimuMusic-File\\" + newFileName));
        String source = "D:\\HimuMusic-File\\" + newFileName;


        //添加source
        musicService.update(source, musicId);

    }

    @GetMapping("{musicId}/download")
    public void downloadMusicFile(@PathVariable Long musicId, HttpServletResponse response) {
        Music entity = musicService.getById(musicId);
        File file = new File(entity.getSource());

        if (file.exists()) {
            response.setContentType("audio/mpeg");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());

            try (FileInputStream fis = new FileInputStream(file);
                 OutputStream os = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();

            } catch (IOException e) {
                // e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 搜索音乐
     * @param query
     * @return
     */
    @GetMapping("/search")
    public List<Music> search(String query) {
        return musicService.musicSearch(query);
    }
}
