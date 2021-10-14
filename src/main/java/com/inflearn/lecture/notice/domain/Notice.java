package com.inflearn.lecture.notice.domain;

import com.inflearn.lecture.domain.Lecture;
import com.inflearn.member.domain.Member;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
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
    private final List<NoticeComment> noticeComment = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member instructor;

    @Builder
    public Notice(String title, String content, Lecture lecture, Member instructor) {
        validateArgument(title, content, lecture, instructor);
        this.title = title;
        this.content = content;
        this.lecture = lecture;
        this.instructor = instructor;
        this.active = true;
    }

    private void validateArgument(String title, String content, Lecture lecture, Member instructor) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("타이틀은 비어있을 수 없습니다.");
        }
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("내용은 비어있을 수 없습니다.");
        }
        if (Objects.isNull(lecture)) {
            throw new IllegalArgumentException("강의는 비어있을 수 없다.");
        }
        if (Objects.isNull(instructor)) {
            throw new IllegalArgumentException("지식공유자는 비어있을 수 없다.");
        }
        if (!instructor.isInstructor()) {
            throw new IllegalArgumentException("지식공유자가 아닙니다.");
        }
    }

    public void update(Notice notice) {
        this.title = notice.title;
        this.content = notice.content;
    }

    public void remove() {
        this.active = false;
    }
}
