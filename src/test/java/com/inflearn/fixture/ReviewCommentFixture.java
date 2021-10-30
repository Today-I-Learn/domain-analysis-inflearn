package com.inflearn.fixture;

import com.inflearn.lecture.review.domain.Review;
import com.inflearn.lecture.review.domain.ReviewComment;
import com.inflearn.member.domain.Member;

public class ReviewCommentFixture {

    public static ReviewComment 리뷰댓글(String content) {
        return 리뷰댓글(ReviewFixture.리뷰(), MemberFixture.지식공유자(), content);
    }

    public static ReviewComment 리뷰댓글(Review review, Member member, String content) {
        return new ReviewComment(review, member, content);
    }
}
