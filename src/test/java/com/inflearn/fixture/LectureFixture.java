package com.inflearn.fixture;

import com.inflearn.lecture.domain.*;
import com.inflearn.member.domain.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LectureFixture {

    public static final Long ID = 1L;
    public static final String TITLE = "제목";
    public static final Price PRICE = new Price(3000);
    public static final String INTRODUCE = "강의를 소개합니다!";
    public static final Member INSTRUCTOR = MemberFixture.지식공유자();
    public static final List<Chapter> CHAPTERS = Arrays.asList(ChapterFixture.챕터());
    public static final List<Tag> TAGS = new ArrayList<>();

    public static Lecture 승인_대기중_강의() {
        return 강의(Approval.WAITING);
    }

    public static Lecture 승인_거절된_강의() {
        return 강의(Approval.REJECTED);
    }

    public static Lecture 승인_완료된_강의() {
        return 강의(Approval.APPROVED);
    }

    private static Lecture 강의(final Approval approval) {
        return Lecture.builder()
                .title(TITLE)
                .price(PRICE)
                .introduce(INTRODUCE)
                .instructor(INSTRUCTOR)
                .chapters(CHAPTERS)
                .approved(approval)
                .build();
    }
}
