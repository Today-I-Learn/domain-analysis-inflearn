package com.inflearn.fixture;

import com.inflearn.cart.domain.Cart;
import com.inflearn.cart.domain.FakeCart;

public class CartFixture {
    public static Cart 수강바구니_active() {
        return Cart.builder()
                .memberId(MemberFixture.회원().getId())
                .active(true)
                .build();
    }

    public static FakeCart 페이크_수강바구니() {
        return new FakeCart();
    }
}
