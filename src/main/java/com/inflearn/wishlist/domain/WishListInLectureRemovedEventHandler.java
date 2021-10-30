package com.inflearn.wishlist.domain;

import com.inflearn.cart.domain.WishListInLectureRemovedEvent;
import com.inflearn.common.domain.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@DomainService
public class WishListInLectureRemovedEventHandler {

    private final WishListRepository wishListRepository;

    @Async
    @EventListener
    @Transactional
    public void listen(WishListInLectureRemovedEvent wishListInLectureRemovedEvent) {
        log.info("Listen WishListInLectureRemovedEvent");
        WishList wishList = wishListRepository.findByMemberId(wishListInLectureRemovedEvent.getMemberId());
        wishList.removeByEvent(wishListInLectureRemovedEvent.getLecture());
    }
}
