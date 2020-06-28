package com.afterwin.product.controller;

import com.afterwin.product.api.UserApi;
import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhengchj
 * @create: 2020-04-29 09:32
 **/
@RestController
public class ProductController {
    @Autowired
    private UserApi userApi;
    private int i = 0;
    @GetMapping("/show/product")
    public String queryProduct(){
        String username = userApi.name();
        return username + "'s product is computer";
    }
    @GetMapping("/count")
    public int count(){
        i++;
        return i;
    }

}
