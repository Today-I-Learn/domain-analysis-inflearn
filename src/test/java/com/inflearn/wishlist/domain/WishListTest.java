package com.inflearn.wishlist.domain;

import com.inflearn.fixture.LectureFixture;
import com.inflearn.fixture.WishListFixture;
import com.inflearn.lecture.domain.Lecture;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("위시리스트(WishList)는")
class WishListTest {

    /*
    - 위시리스트는 회원만 관리할 수 있다.
    - 위시리스트에 추가할 수 있다.
        - 위시리스트에 담으면 추가된다.
        - 강의 ID가 없다면 추가할 수 없다.
    - 수강바구니에 담을 수 있다.
        - 위시리스트에서 삭제된다.
    - 위시리스트 목록에서 강의를 삭제할 수 있다.
    - 위시리스트 목록을 조회할 수 있다.
     */

    private WishList wishList;

    @BeforeEach
    void setUp() {
        wishList = WishListFixture.위시리스트_active();
    }

    @DisplayName("강의를 추가할 수 있다.")
    @Test
    public void AddLecture() {
        // when
        wishList.add(LectureFixture.승인_완료된_강의());

        // then
        assertThat(wishList.getLectures()).isNotEmpty();
    }

    @DisplayName("강의를 삭제할 수 있다.")
    @Test
    public void removeLecture() {
        // given
        Lecture lecture = LectureFixture.ID_유효한_강의();
        wishList.add(lecture);

        // when
        wishList.remove(lecture);

        // then
        assertThat(wishList.getLectures()).isEmpty();
    }

    @DisplayName("중복된 강의는 추가할 수 없다.")
    @Test
    public void AddDuplicateLecture() {
        // given
        Lecture lecture = LectureFixture.ID_유효한_강의();
        wishList.add(lecture);

        // when
        ThrowableAssert.ThrowingCallable throwingException = () -> wishList.add(lecture);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("존재하지 않는 강의는 삭제할 수 없다")
    @Test
    public void RemoveNonExistLecture() {
        //when
        ThrowableAssert.ThrowingCallable throwingException = () -> wishList.remove(LectureFixture.ID_유효한_강의());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }
}
