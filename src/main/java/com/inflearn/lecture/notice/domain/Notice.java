package com.inflearn.lecture.notice.domain;

import com.inflearn.member.domain.Member;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "notice_comment_id",
            nullable = false,
            columnDefinition = "bigint(20)",
            foreignKey = @ForeignKey(name = "fk_notice_comment_to_notices")
    )
    private List<NoticeComment> noticeComment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member instructor;

    public void update(Notice notice) {
        this.title = notice.title;
        this.content = notice.content;
    }

    public void remove() {
        this.active = false;
    }
}
