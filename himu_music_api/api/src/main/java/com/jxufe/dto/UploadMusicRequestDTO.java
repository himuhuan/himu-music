package com.jxufe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadMusicRequestDTO {
    private String title;
    private String firstPerformer;
    private String source;
    private String albumPictureBase64;
    private Long duration;
    private long issuerId;
    // private MultipartFile musicFile;
}
