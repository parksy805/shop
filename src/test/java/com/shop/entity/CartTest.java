package com.shop.entity;

import com.shop.dto.MemberFormDto;
import com.shop.repository.CartRepository;
import com.shop.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember(){ //회원생성
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }
    
    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void finalCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member); //회원정보

        Cart cart = new Cart(); //장바구니 생성
        cart.setMember(member); // 해당 회원의 cart로
        cartRepository.save(cart); // cart에 저장

        em.flush(); //영속성... flush로 호출하여 DB에 반영
        em.clear(); //entity조회, DB조회,....

        Cart saveCart = cartRepository.findById(cart.getId()) //cartRepository에서 장바구니 select 쿼리 자동실행
                .orElseThrow(EntityNotFoundException::new); //throws SQLException 같은거?
        assertEquals(saveCart.getMember().getId(), member.getId());//장바구니의 Id와 회원의 Id 비교
    }

}
