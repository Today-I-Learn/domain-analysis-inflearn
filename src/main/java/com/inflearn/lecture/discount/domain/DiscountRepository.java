package com.inflearn.lecture.discount.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    // TODO: 2021/10/18 쿼리 틀림 수정 필요  
    @Query("SELECT discount FROM Discount discount WHERE discount.id = :lectureId ORDER BY discount.ratio DESC")
    Discount findTopByLectureId(Long lectureId);

    // 1. ㄱㅏㅇ의 조회해오고 = Lecture
    // 2. 강의 아이디 + 할인 기간 만료 안된 할인 들 불러오기 = List<Discount>
    // 3. List<Discout> 할인율 가장 높은거로 DTO Lecutere + DIscout -> LecResponse
}
