package com.afterwin.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhengchj
 * @create: 2020-04-27 19:18
 **/
@RestController
@RefreshScope
public class UserController {
    @Value("${user.name}")
    private String name;
    @GetMapping(value = "/print/{string}")
    public String print(@PathVariable String string) {
        return "print: " + string;
    }
    @GetMapping(value = "/name")
    public String name() {
        return "print: " + name;
    }
}
