package com.inflearn.discount.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    // TODO: 2021/10/18 쿼리 틀림 수정 필요  
    @Query("SELECT discount FROM Discount discount WHERE discount.id = :lectureId ORDER BY discount.ratio DESC")
    Discount findTopByLectureId(Long lectureId);
}