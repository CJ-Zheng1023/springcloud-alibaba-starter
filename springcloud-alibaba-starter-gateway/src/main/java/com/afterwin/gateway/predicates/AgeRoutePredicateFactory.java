package com.afterwin.gateway.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @description: 自定义的网关断言工厂。具体业务逻辑为接收age为一定区间的请求
 * @author: zhengchj
 * @create: 2020-05-02 15:34
 **/
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    /**
     * 断言逻辑
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
            if(StringUtils.isEmpty(ageStr)){
                return false;
            }
            int age = Integer.parseInt(ageStr);
            return age <= config.getMax() && age >= config.getMin();
        };
    }

    @Data
    @NoArgsConstructor
    static class Config{
        private int min;
        private int max;
    }
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取文件中的参数
     * @return
     */
    public List<String> shortcutFieldOrder() {
        //读取配置文件，按顺序给Config配置类赋值
        return Arrays.asList("min", "max");
    }

}
