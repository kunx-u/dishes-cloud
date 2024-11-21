package com.etoak.filter;

import cn.hutool.json.JSONUtil;
import com.etoak.common.core.properties.ImageProperties;
import com.etoak.common.core.vo.ResultVO;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.properties.IgnoreProperties;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    IgnoreProperties ignoreProperties;

    @Autowired
    ImageProperties imageProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //请求地址
        String uri = request.getPath().value();
        if(ignoreProperties.getUriList().contains(uri) || uri.startsWith(imageProperties.getMapping())){
            return chain.filter(exchange);
        }

        /* 校验token */
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(token)){
            return noAuth(response,"请传入令牌");
        }
        try{
            JwtUtil.parse(token);
            return chain.filter(exchange);
        }catch (ExpiredJwtException e){
            return noAuth(response,"令牌过期了!");
        }catch (Exception e){
            return noAuth(response,"令牌错误!");
        }


    }

    private Mono<Void> noAuth(ServerHttpResponse response, String message) {
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE,"application/json");

        ResultVO<Object> resultVO = ResultVO.failed(403, message);
        String jsonStr = JSONUtil.toJsonStr(resultVO);

        DataBuffer dataBuffer = response.bufferFactory().wrap(jsonStr.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
