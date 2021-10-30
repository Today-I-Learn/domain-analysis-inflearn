package com.inflearn.cart.domain;

import lombok.Getter;

@Getter
public class FakeCart extends Cart {
    private boolean regist;

    @Override
    protected <PublishedEvent> PublishedEvent registerEvent(PublishedEvent event) {
        this.regist = true;
        return super.registerEvent(event);
    }
}
