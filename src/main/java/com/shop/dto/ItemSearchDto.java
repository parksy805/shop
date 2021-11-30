package com.shop.dto;


import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;
    
    private ItemSellStatus searchSellStatus; // SELL SOLDOUT
    
    // itemNM 상품명, createBy: 상품 등록자 아이디
    private String searchBy;
    
    // 조회할 검색어 저장
    private String searchQuery="";


    

}
