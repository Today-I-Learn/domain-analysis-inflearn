package com.inflearn.lecture.notice.domain;

import com.inflearn.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class NoticeReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void update(NoticeReply noticeReply) {
        this.content = noticeReply.content;
    }

    public void remove() {
        this.active = false;
    }
}
