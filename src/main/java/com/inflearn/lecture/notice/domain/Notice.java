package com.inflearn.lecture.notice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean active;

    @OneToMany
    private List<NoticeReply> noticeReply;

    public void update(Notice notice) {
        this.title = notice.title;
        this.content = notice.content;
    }

    public void remove() {
        this.active = false;
    }
}
