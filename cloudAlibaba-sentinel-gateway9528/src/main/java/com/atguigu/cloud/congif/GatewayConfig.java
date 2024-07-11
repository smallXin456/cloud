package com.atguigu.cloud.congif;


import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class GatewayConfig {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;
    public GatewayConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider
            ,ServerCodecConfigurer serverCodecConfigurer){
        this.viewResolvers=viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer=serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler(){
        return new SentinelGatewayBlockExceptionHandler(viewResolvers,serverCodecConfigurer);
    }
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter(){
        return new SentinelGatewayFilter();
    }
    @PostConstruct
    public void doInit(){
        //自己动手
        initBlockHandler();
    }



    //处理+自定义返回的例外信息内容
    private void initBlockHandler(){
        Set<GatewayFlowRule> rules = new HashSet<>();
        //添加规则,1秒钟2次
        rules.add(new GatewayFlowRule("pay_routh1")
                .setCount(2)
                .setIntervalSec(1)
        );
        //加载
        GatewayRuleManager.loadRules(rules);

        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            //exchange 相当于request
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
                Map<String, String> map = new HashMap<>();

                map.put("errorCode", HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
                map.put("errorMessage","请求太频繁");

                //ServerResponse 相当于 response

                return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);

    }

}
