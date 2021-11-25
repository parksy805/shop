package com.shop.dto;


import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private Long id;  // 상품코드
    private String itemNm; // 상품명
    private int price; // 가격
    private String itemDetail; // 상품 상세 설명
    private String sellStatCd; // 상품 판매 상태
    private LocalDateTime regTime; // 등록시간
    private LocalDateTime updateTime; // 수정 시간

}
