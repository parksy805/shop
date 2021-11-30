package com.shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id") // 주문내역 상품의 id가 새로 생성 기준
    private Long id;

    //@ManyToOne의 FetchType은 EAGER가 기본값이라서 LAZY로 바꿈
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id") //상품 id
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // 주문 번호
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 수량


//    private LocalDateTime regTime;
//    private LocalDateTime updateTime;

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //주문할 '상품' 정하고
        orderItem.setCount(count);//주문할 '수량' 정하고

        orderItem.setOrderPrice(item.getPrice()); //'가격'을 '주문가격'으로 설정

        item.removeStock(count);
        return orderItem;
    }

    public int getTotalPrice() { // 상품 1개에 대한 총 주문가격
        return orderPrice * count;
    }

}
