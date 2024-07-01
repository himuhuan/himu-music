package com.jxufe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    private Long id;
    private String title;
    private String firstPerformer;
    private String source;
    private String albumPictureBase64;
    private Long duration;
    private Long issuerId;
}
