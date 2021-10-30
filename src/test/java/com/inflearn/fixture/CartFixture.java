package com.inflearn.fixture;

import com.inflearn.cart.domain.Cart;

public class CartFixture {
    public static Cart 수강바구니_active() {
        return Cart.builder()
                .memberId(MemberFixture.회원().getId())
                .active(true)
                .build();
    }
}
