package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "cart_item")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id") //회원별로 cart_id를 부여함
    private  Cart cart; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id") // Cartitem테이블에 item_id 부여
    private Item item;

    private int count;


}
