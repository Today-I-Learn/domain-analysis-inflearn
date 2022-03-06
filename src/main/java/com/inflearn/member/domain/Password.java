package com.inflearn.member.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Password {
    // 최소 숫자 1자리 이상, 영어 소문자 1자리 이상, 영어 대문자 1자리 이상, 특수문자 1자리 이상, 공백문자 포함하지 않고, 8자리 이상
    private static final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&+=])(?=\\S+$).{8,}$");

    @Column(name = "password")
    private String value;

    protected Password() {
    }

    Password(String password) {
        verify(password);
        this.value = password;
    }

    public static Password of(String password) {
        return new Password(password);
    }

    private void verify(String password) {
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("비밀번호는 필수값입니다.");
        }
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("비밀번호 형식을 확인해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password password1 = (Password) o;
        return Objects.equals(value, password1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
