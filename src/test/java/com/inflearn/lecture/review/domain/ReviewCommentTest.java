package com.inflearn.lecture.review.domain;

import com.inflearn.fixture.MemberFixture;
import com.inflearn.fixture.ReviewCommentFixture;
import com.inflearn.fixture.ReviewFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("리뷰 댓글(ReviewComment)은")
public class ReviewCommentTest {

    // ReviewComment를 Review에 추가할 수 있다. (리뷰에 댓글을 달 수 있다.) <-
    // Review는 ReviewComment를 추가할 수 있다? Reivew ->
    // Chatper를 Lecture에 추가할 수 있다???
    // Lecture는 Chapter를 추가할 수 있다 -> O
    // 관리하는 주체가 누구인지??

    @DisplayName("생성할 수 있다.")
    @Test
    void create() {
        String content = "작성 내용";
        ReviewComment reviewComment = new ReviewComment(ReviewFixture.리뷰(), MemberFixture.지식공유자(), content);

        Assertions.assertThat(reviewComment.getContent()).isEqualTo(content);
        Assertions.assertThat(reviewComment.isActive()).isTrue();
    }

    @DisplayName("수정할 수 있다.")
    @Test
    void update() {
        String newContent = "바꿀 내용";
        ReviewComment reviewComment = ReviewCommentFixture.리뷰댓글("내용");

        reviewComment.update(newContent);
        Assertions.assertThat(reviewComment.getContent()).isEqualTo(newContent);
    }

    @DisplayName("삭제할 수 있다.")
    @Test
    void delete() {
        ReviewComment reviewComment = ReviewCommentFixture.리뷰댓글("내용");
        Assertions.assertThat(reviewComment.isActive()).isTrue();

        reviewComment.delete();

        Assertions.assertThat(reviewComment.isActive()).isFalse();
    }

}