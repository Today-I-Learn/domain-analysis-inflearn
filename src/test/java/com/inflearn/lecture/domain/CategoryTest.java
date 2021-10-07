package com.inflearn.lecture.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CategoryTest {

    @DisplayName("카테고리를 생성할 수 있다.")
    @Test
    void create() {
        Category category = new Category("개발 · 프로그래밍");

//        assertThat(category).isEqualTo(new Category("개발 · 프로그래밍"));
    }

    @DisplayName("카테고리 이름은 비어있을 수 없다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createFail(String name) {
        ThrowingCallable throwingCallable = () -> new Category(name);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(throwingCallable);
    }

    @DisplayName("부모 카테고리는 하위 카테고리를 가져올 수 있다.")
    @Test
    void root_retrieve_subcategory() {
        Category root = new Category("개발 · 프로그래밍");
        Category category = new Category(root, "웹 개발");

        assertThat(root.getSubCategory()).contains(category);
    }

    @DisplayName("자식 카테고리는 하위 카테고리를 접근하면 IllegalStateException을 발생한다.")
    @Test
    void sub_retrieve_subcategory_fail() {
        Category subCategory = new Category(new Category("부모"), "하위");

        ThrowingCallable throwingCallable = () -> subCategory.getSubCategory();

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(throwingCallable);
    }
}
