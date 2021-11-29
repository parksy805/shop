package com.shop.entity;


import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order라는 키워드가 이미 있어서 orders로 이름 지음
@Getter @Setter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY) //1명의 회원이 여러개 주문을 할 수 있으니까
    @JoinColumn(name = "member_id") //회원id를 참조해라
    private Member member;

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문상태
    
    //OneToMany fetchType은 LAZY가 기본값
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, // OrderItem클래스를 주인으로 하고 그 안에 있는 order가 주요인이 됨..
                                    orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


//    private LocalDateTime regTime; //등록일?
//    private LocalDateTime updateTime; //수정일?


}
