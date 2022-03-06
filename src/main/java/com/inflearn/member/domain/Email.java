package com.inflearn.member.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Embeddable
public final class Email {
    // 참고: 이펙티브 자바 6장
    private static final Pattern pattern = Pattern.compile("^(.+)@(.+)$");

    @Column(name = "email")
    private String value;

    protected Email() {
    }

    Email(String email) {
        verify(email);
        this.value = email;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    private void verify(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("이메일 형식은 비울수없어용.");
        }
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("이메일 형식을 확인해주세요.");
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
        Email email1 = (Email) o;
        return Objects.equals(value, email1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
