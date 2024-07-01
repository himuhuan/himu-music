package com.jxufe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jxufe.mapper")
public class HimuMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(HimuMusicApplication.class, args);
    }

}
