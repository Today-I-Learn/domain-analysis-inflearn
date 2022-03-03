package com.inflearn.member.repository;

import static org.assertj.core.api.Assertions.*;

import com.inflearn.member.domain.Member;
import com.inflearn.member.domain.MemberRepository;
import com.inflearn.member.exception.MemberNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 멤버_조회_성공() throws Exception {
        //given
        Long id = 1L;
        //when
        Member member = memberRepository.findById(id)
                                        .orElseThrow(() -> new MemberNotFoundException("존재 하지 않는 ID : " + id));
        //then
        assertThat(member.getId()).isEqualTo(id);
    }

}
