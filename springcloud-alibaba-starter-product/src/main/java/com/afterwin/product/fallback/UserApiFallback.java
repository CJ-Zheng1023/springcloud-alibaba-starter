package com.afterwin.product.fallback;

import com.afterwin.product.api.UserApi;
import org.springframework.stereotype.Component;

/**
 * @description: UserApi的容错类
 * @author: zhengchj
 * @create: 2020-05-02 11:21
 **/
@Component
public class UserApiFallback implements UserApi {
    @Override
    public String name() {
        return "no name,because i am fallback";
    }
}
