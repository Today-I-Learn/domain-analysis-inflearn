package com.inflearn.lecture.review.domain;

import com.inflearn.fixture.LectureFixture;
import com.inflearn.fixture.MemberFixture;
import com.inflearn.fixture.ReviewFixture;
import com.inflearn.lecture.domain.Lecture;
import com.inflearn.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("리뷰(Review)는")
public class ReviewTest {

    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
        Member student = MemberFixture.회원();
        Lecture lecture = LectureFixture.승인_완료된_강의();
        Rating rating = new Rating(5);
        String content = "작성 내용";

        Review review = new Review(student, lecture, rating, content);

        Assertions.assertThat(review.getContent()).isEqualTo(content);
        Assertions.assertThat(review.isActive()).isTrue();
    }

    @DisplayName("수정할 수 있다.")
    @Test
    void update() {
        Rating rating = new Rating(3);
        String content = "바꿀 내용";
        Review review = ReviewFixture.리뷰();

        review.update(new Review(rating, content));

        Assertions.assertThat(review.getRating()).isEqualTo(rating);
        Assertions.assertThat(review.getContent()).isEqualTo(content);
    }

    @DisplayName("삭제할 수 있다.")
    @Test
    void delete() {
        Review review = ReviewFixture.리뷰();
        Assertions.assertThat(review.isActive()).isTrue();

        review.remove();

        Assertions.assertThat(review.isActive()).isEqualTo(false);
    }
}