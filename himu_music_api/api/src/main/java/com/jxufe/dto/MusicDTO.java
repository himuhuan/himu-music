package com.jxufe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicDTO {
    private Long id;
    private String title;
    private String firstPerformer;
    private String source;
    private String albumPictureBase64;
    private Long duration;
    private Long issuerId;
}
