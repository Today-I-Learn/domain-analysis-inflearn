package com.inflearn.cart.domain;

import com.inflearn.common.domain.annotation.DomainService;
import com.inflearn.wishlist.domain.CartInLectureRemovedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@DomainService
public class CartInLectureRemovedEventHandler {

    private final CartRepository cartRepository;

    @Async
    @EventListener
    @Transactional
    public void listen(CartInLectureRemovedEvent cartInLectureRemovedEvent) {
        log.info("Listen CartInLectureRemovedEvent");
        Cart cart = cartRepository.findByMemberId(cartInLectureRemovedEvent.getMemberId());
        cart.removeByEvent(cartInLectureRemovedEvent.getLecture());
    }

}
