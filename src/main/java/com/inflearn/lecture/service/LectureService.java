//package com.inflearn.lecture.service;
//
//import com.inflearn.lecture.domain.Lecture;
//import com.inflearn.lecture.domain.LectureRepository;
//import com.inflearn.lecture.dto.LectureResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class LectureService {
//
//    private final LectureRepository lectureRepository;
//    private final LectureDiscountService lectureDiscountService;
//
//    public List<LectureResponse> find() {
//        List<Lecture> lectures = lectureRepository.findAll();
//        return lectureDiscountService.discount(lectures);
//    }
//
//}
