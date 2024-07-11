package com.atguigu.cloud.mygateway;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

//自定义配置会员等级userType，按照砖/金/银和yml配置的会员等级，来适配是否可以访问
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {


    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    //验证注解
    @Validated
    public static class Config {
        @Setter @Getter @NotNull
        private String userType;
    }


    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {

                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if (userType == null) {
                    return false;
                }

                if (userType.equalsIgnoreCase(config.getUserType())){
                    return true;
                }
                return false;
            }

        };


    }







}
