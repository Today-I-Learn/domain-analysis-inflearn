package com.inflearn.lecture.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Nullable
    private String video;

    @Nullable
    private String document;

    private Duration playTime;

    private boolean displayed = false;

    public Chapter(String title, String video, String document) {
        this(null, title, video, document, Duration.ZERO, false);
    }

    public Chapter(String title, String video, String document, Duration playTime) {
        this(null, title, video, document, playTime, false);
    }

    public Chapter(String title, String video, String document, Duration playTime, boolean displayed) {
        this(null, title, video, document, playTime, displayed);
    }

    public Chapter(Long id, String title, String video, String document, Duration playTime, boolean displayed) {
        verify(title);
        this.id = id;
        this.title = title;
        this.video = video;
        this.document = document;
        this.playTime = playTime;
        this.displayed = displayed;
    }

    private void verify(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("비어있는 타이틀 불가 by 09학번");
        }
    }

}
