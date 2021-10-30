package com.inflearn.fixture;

import com.inflearn.wishlist.domain.WishList;

public class WishListFixture {

    public static WishList 위시리스트_active() {
        return WishList.builder()
                .memberId(MemberFixture.회원().getId())
                .active(true)
                .build();
    }
}
