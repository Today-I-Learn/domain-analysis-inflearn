package com.inflearn.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByEmail(Email email);
  @Override
  Optional<Member> findById(Long id);
}
