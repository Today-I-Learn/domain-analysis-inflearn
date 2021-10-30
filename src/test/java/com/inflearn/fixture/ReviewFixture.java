package com.inflearn.fixture;

import com.inflearn.lecture.domain.Lecture;
import com.inflearn.lecture.review.domain.Rating;
import com.inflearn.lecture.review.domain.Review;
import com.inflearn.member.domain.Member;

public class ReviewFixture {

    public static final String CONTENT = "악플";
    public static final Rating RATING = new Rating(1);

    public static Review 리뷰() {
        return 리뷰(MemberFixture.회원(), LectureFixture.승인_완료된_강의(), RATING, CONTENT);
    }

    public static Review 리뷰(String content, Rating rating) {
        return 리뷰(MemberFixture.회원(), LectureFixture.승인_완료된_강의(), rating, content);
    }

    public static Review 리뷰(Member student, Lecture lecture, Rating rating, String content) {
        return new Review(student, lecture, rating, content);
    }

}
