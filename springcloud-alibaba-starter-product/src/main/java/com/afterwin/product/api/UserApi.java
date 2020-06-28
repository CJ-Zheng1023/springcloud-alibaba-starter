package com.afterwin.product.api;

import com.afterwin.product.fallback.UserApiFallback;
import com.afterwin.product.fallback.UserApiFallbackFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: zhengchj
 * @create: 2020-04-29 09:28
 **/
//@FeignClient(name = "user-service", fallback = UserApiFallback.class)
@FeignClient(name = "user-service",
        //fallback = UserApiFallback.class
        fallbackFactory = UserApiFallbackFactory.class
)
public interface UserApi {
    @GetMapping(value = "/name")
    String name();
}
