package com.inflearn.cart.domain;


import com.inflearn.lecture.domain.Lecture;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @OneToMany
    private final List<Lecture> lectures = new ArrayList<>();

    private boolean active;


    public void add(Lecture lecture) {
        if (lectures.contains(lecture)) {
            throw new IllegalArgumentException("중복된 강의는 추가할 수 없다.");
        }

        lectures.add(lecture);
    }

    public void remove(Lecture lecture) {
        if (!lectures.contains(lecture)) {
            throw new IllegalArgumentException("수강 바구니에 존재하지 않는 강의입니다.");
        }

        lectures.remove(lecture);
    }

    public void removeAll() {
        lectures.clear();
    }
}
