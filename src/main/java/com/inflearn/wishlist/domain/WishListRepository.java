package com.inflearn.wishlist.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByMemberId(Long memberId);
}
