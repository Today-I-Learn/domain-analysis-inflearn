package com.inflearn.lecture.review.domain;

import com.inflearn.common.domain.BaseEntity;
import com.inflearn.lecture.domain.Lecture;
import com.inflearn.member.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member student;

    @ManyToOne
    private Lecture lecture;

    @Embedded
    private Rating rating;

    // TODO: 2021/10/07 글자수 제한 필요 
    private String content;

    // TODO: 2021/10/13 읽기 모델인데, 관계를 가지고 있어야할 필요가 있을까? 
    @OneToMany
    private List<ReviewComment> comments = new ArrayList<>();

    private boolean active = true;

    protected Review() {

    }

    public Review(Rating rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    // TODO: 2021/10/07 좋아요 기능 구현해야함 ReviewComment에도 ditto
    public Review(Member student, Lecture lecture, Rating rating, String content) {
        this.student = student;
        this.lecture = lecture;
        this.rating = rating;
        this.content = content;
    }

    public void remove() {
        this.active = false;
    }

    public void update(Review review) {
        this.rating = review.rating;
        this.content = review.content;
    }

    public Member getStudent() {
        return student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public Rating getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public List<ReviewComment> getComments() {
        return comments;
    }

    public boolean isActive() {
        return active;
    }
}
