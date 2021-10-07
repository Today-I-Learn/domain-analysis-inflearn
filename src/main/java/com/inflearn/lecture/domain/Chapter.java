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

    private boolean preview = false; // 미리보기 제공 여부

    private boolean active = true; // 활성 여부 (false 시 삭제됨)

    public Chapter(String title, String video, String document) {
        this(null, title, video, document, Duration.ZERO, false, true);
    }

    public Chapter(String title, String video, String document, Duration playTime) {
        this(null, title, video, document, playTime, false, true);
    }

    public Chapter(String title, String video, String document, Duration playTime, boolean preview) {
        this(null, title, video, document, playTime, preview, true);
    }

    public Chapter(Long id, String title, String video, String document, Duration playTime, boolean preview, boolean active) {
        verify(title);
        this.id = id;
        this.title = title;
        this.video = video;
        this.document = document;
        this.playTime = playTime;
        this.preview = preview;
        this.active = active;
    }

    private void verify(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("비어있는 타이틀 불가 by 09학번");
        }
    }

    public void remove() {
        this.active = false;
    }

    public void update(final Chapter chapter) {
        this.title = chapter.title;
        this.video = chapter.video;
        this.document = chapter.document;
        this.playTime = chapter.playTime;
        this.preview = chapter.preview;
    }
}
