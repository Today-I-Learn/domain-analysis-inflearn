package com.inflearn.wishlist.domain;

import lombok.*;

@Getter
public class FakeWishList extends WishList{
    private boolean regist;

    @Override
    protected <PublishedEvent> PublishedEvent registerEvent(PublishedEvent event) {
        this.regist = true;
        return super.registerEvent(event);
    }
}
