//package com.inflearn.lecture.service;
//
//import com.inflearn.discount.domain.Discount;
//import com.inflearn.discount.domain.DiscountRepository;
//import com.inflearn.lecture.domain.Lecture;
//import com.inflearn.lecture.dto.LectureResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class LectureDiscountService {
//    
//    private final DiscountRepository discountRepository;
//    
//    public List<LectureResponse> discount(List<Lecture> lectures) {
//        lectures.stream()
//                .map(it -> discountA(it.getId()))
//                .collect(Collectors.toList());
//    }
//
//    private Object discountA(Long lectureId) {
//        Discount discount = discountRepository.findTopByLectureId(lectureId);
//
//    }
//}
//
///*
//        # 쿠폰
//
//        - 쿠폰을 발급할 수 있다.
//        - 쿠폰 발급자만 가능하다.
//        - 발급자는 관리자와 해당 강의의 지식공유자이다.
//        - 강의 ID, 쿠폰 번호, 만료기간이 필요하다.
//        - 쿠폰을 등록할 수 있다.
//        - 회원은 쿠폰 등록을 시도할 수 있다.
//        - 쿠폰 번호가 올바르지 않으면 쿠폰을 등록할 수 없다.
//        - 만료된 쿠폰은 등록할 수 없다.
//        - 쿠폰을 사용할 수 있다.
//        - 회원은 쿠폰을 사용할 수 있다.
//        - 해당 강의를 결제할 때 적용할 수 있다.
//        - 쿠폰을 중복해서 적용할 수 없다.
//        - 만료된 쿠폰을 사용할 수 없다.
//        - 쿠폰이 만료 3일 전에 알림에 대한 메일이 발송된다.
//        - 회원 이메일이 올바르지 않으면 이메일을 보낼 수 없다.
//        - 쿠폰을 부활시킬 수 있다.
//        - 만료된 쿠폰 중에서 관리자가 발급한 쿠폰만 부활시킬 수 있다.
//        - 쿠폰은 단 1회 부활 시킬 수 있다.
//        - 부활시킨 쿠폰은 3일이 지나면 만료된다.
//        - 회원 이메일이 올바르지 않다면 이메일을 보낼 수 없다.
//        - 등록한 쿠폰 목록을 조회할 수 있다.*/
