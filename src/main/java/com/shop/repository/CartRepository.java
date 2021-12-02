package com.shop.repository;


import com.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    // extends JpaRepository<Cart, Long> 가 DB에 select, insert, delete 알아서 쿼리 실행

    Cart findByMemberId(Long memberId);





}
