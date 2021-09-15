package com.inflearn.lecture.domain;

import lombok.Builder;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Builder
public class Chapter {

    private final UUID id = UUID.randomUUID();

    private final String title;

    @Nullable
    private final String video;

    @Nullable
    private final String document;

    // TODO: 2021/09/13 어떤걸로 할까요? 리서치좀..
    private final Instant playTime;

    private boolean displayed = false;

    public Chapter(String title, String video, String document, Instant playTime) {
        this(title, video, document, playTime, false);
    }

    public Chapter(String title, String video, String document, Instant playTime, boolean displayed) {
        verify(title);
        this.title = title;
        this.video = video;
        this.document = document;
        this.playTime = playTime;
        this.displayed = displayed;
    }

    public static class ChapterBuilder {
        public ChapterBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ChapterBuilder video(String video) {
            this.video = video;
            return this;
        }

        public ChapterBuilder document(String document) {
            this.document = document;
            return this;
        }

        public ChapterBuilder playTime(Instant playTime) {
            this.playTime = playTime;
            return this;
        }

        public ChapterBuilder displayed(boolean displayed) {
            this.displayed = displayed;
            return this;
        }
    }

    private void verify(String title) {
        if(!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("비어있는 타이틀 불가 by 09학번");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id)
                || (displayed == chapter.displayed
                && Objects.equals(title, chapter.title)
                && Objects.equals(video, chapter.video)
                && Objects.equals(document, chapter.document)
                && Objects.equals(playTime, chapter.playTime));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, video, document, playTime, displayed);
    }
}
