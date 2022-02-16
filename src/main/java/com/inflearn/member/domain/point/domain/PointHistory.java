package com.inflearn.member.domain.point.domain;

// 포인트가 사용된 것을 기록하기 위함
// 결제 취소 및 환불시 포인트로 결제된 내역을 환불하기 위함인데
// 포인트에서 단일 사용 항목만 가지고 있는 경우 여러개의 환불 취소에 대해서 롤백이 불가능함
// 때문에 포인트가 사용될 때 각각 언제 얼마가 사용되었는지 트래킹할 수 있는 히스토리가 필요
public class PointHistory {



}
