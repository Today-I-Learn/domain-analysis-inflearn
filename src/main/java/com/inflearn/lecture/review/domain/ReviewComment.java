package com.inflearn.lecture.review.domain;

import com.inflearn.common.domain.BaseEntity;
import com.inflearn.member.domain.Member;

import javax.persistence.*;

@Entity
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member writer;

    // TODO: 2021/10/07 Lob 달꺼말꺼? 글자수 제한할꺼말꺼?
    private String content;

    // TODO: 2021/10/07 리뷰 댓글에도 좋아요 가능

    @ManyToOne
    private Review review;

    private boolean active = true;

    protected ReviewComment() {

    }

    public ReviewComment(Review review, Member writer, String content) {
        this.review = review;
        this.writer = writer;
        this.content = content;
    }

    public Member getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public void update(String content) {
        this.content = content;
    }

    public void delete() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }
}
