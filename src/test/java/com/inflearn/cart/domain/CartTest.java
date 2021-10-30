package com.inflearn.cart.domain;

import com.inflearn.fixture.CartFixture;
import com.inflearn.fixture.LectureFixture;
import com.inflearn.lecture.domain.Lecture;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("수강바구니(cart)는")
class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = CartFixture.수강바구니_active();
    }

    @DisplayName("강의를 추가할 수 있다.")
    @Test
    public void AddLecture() {
        // when
        cart.add(LectureFixture.승인_완료된_강의());

        // then
        assertThat(cart.getLectures()).isNotEmpty();
    }

    @DisplayName("강의를 추가시 위시리스트에서 해당 강의를 삭제하는 이벤트를 발행한다.")
    @Test
    public void publishEventByAddLecture() {
        // given
        FakeCart cart = CartFixture.페이크_수강바구니();

        // when
        cart.add(LectureFixture.승인_완료된_강의());

        // then
        assertThat(cart.isRegist()).isTrue();
    }

    @DisplayName("강의를 삭제할 수 있다.")
    @Test
    public void removeLecture() {
        // given
        Lecture lecture = LectureFixture.ID_유효한_강의();
        cart.add(lecture);

        // when
        cart.remove(lecture);

        // then
        assertThat(cart.getLectures()).isEmpty();
    }

    @DisplayName("중복된 강의는 추가할 수 없다.")
    @Test
    public void AddDuplicateLecture() {
        // given
        Lecture lecture = LectureFixture.ID_유효한_강의();
        cart.add(lecture);

        // when
        ThrowableAssert.ThrowingCallable throwingException = () -> cart.add(lecture);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("존재하지 않는 강의는 삭제할 수 없다")
    @Test
    public void RemoveNonExistLecture() {
        //when
        ThrowableAssert.ThrowingCallable throwingException = () -> cart.remove(LectureFixture.ID_유효한_강의());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingException);
    }

    @DisplayName("전체 강의 목록을 삭제할 수 있다.")
    @Test
    public void RemoveAllLecture(){
        //given
        cart.add(LectureFixture.승인_완료된_강의());

        //when
        cart.removeAll();

        //then
        assertThat(cart.getLectures()).isEmpty();
    }
}
