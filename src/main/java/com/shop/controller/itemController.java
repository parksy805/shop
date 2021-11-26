package com.shop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class itemController {

    @GetMapping(value = "/admin/item/new") //운영자만 관리하기 위해서 /admin/
    public String itemForm(){
        return "/item/itemForm";
    }

}
