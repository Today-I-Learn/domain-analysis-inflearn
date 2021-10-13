package com.inflearn.lecture.notice.domain;

import com.inflearn.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class NoticeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void update(NoticeComment noticeComment) {
        this.content = noticeComment.content;
    }

    public void remove() {
        this.active = false;
    }
}
