package com.afterwin.product.fallback;

import com.afterwin.product.api.UserApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: UserApi容错工厂类
 * @author: zhengchj
 * @create: 2020-05-02 12:45
 **/
@Component
@Slf4j
public class UserApiFallbackFactory implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable throwable) {
        return new UserApi() {
            @Override
            public String name() {
                log.error("{}", throwable);
                return "no name, because i am fallback factory";
            }
        };
    }
}
