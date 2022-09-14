package club.kwcoder.vote.Interceptor;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.util.RedisUtils;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteGlobalFilter implements GlobalFilter, Ordered {

    @Value("${jwt.key}")
    private String jwtKey;
    @Autowired
    private RedisUtils redisUtils;
    private final List<String> excludePath;

     {
        excludePath = new ArrayList<>();
        excludePath.add("/common/login");
         excludePath.add("/common/register/do");
         excludePath.add("/common/register/info");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getPath().toString();

        if (excludePath.contains(path)) {
            return chain.filter(exchange);
        }

        String jwt = verifyJWT(request);
        if (StrUtil.isBlank(jwt)) {
            return unauthorized(response);
        }

        JWT jwtParse = JWTUtil.parseToken(jwt);
        JSONObject payloads = jwtParse.getPayloads();
        String phone = payloads.get("phe", String.class);
        String jwtFromRedis = (String) redisUtils.get("jwt:" + phone);

        if (!StrUtil.equals(jwtFromRedis, jwt)) {
            return unauthorized(response);
        }

        String role = payloads.get("rol", String.class);
        role = role.substring(1, role.length() - 1);
        List<String> roles = Arrays.stream(role.split(", ")).collect(Collectors.toList());
        if (roles.contains("ADMIN") && path.startsWith("/admin")) {
            return chain.filter(exchange);
        }
        if (roles.contains("USER") && path.startsWith("/user")) {
            return chain.filter(exchange);
        }
        if ((roles.contains("ADMIN") || role.contains("USER")) && path.startsWith("/common")) {
            return chain.filter(exchange);
        }

        return unauthorized(response);
    }

    public Mono<Void> unauthorized(ServerHttpResponse response) {
        response.getHeaders().clear();
        response.getCookies().clear();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        ResultBean<Object> forbidden = ResultBean.forbidden("登录已失效或尚未登录，请重新登录！");
        byte[] bytes = JSONUtil.toJsonStr(forbidden).getBytes();
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));
    }

    public String verifyJWT(ServerHttpRequest request) {
        String jwt = request.getHeaders().getFirst("authorization");
        if (StrUtil.isBlank(jwt) || !JWTUtil.verify(jwt, jwtKey.getBytes())) {
            return null;
        }
        return jwt;
    }

    @Override
    public int getOrder() {
        //3:系统调用该方法获得过滤器的优先级
        return 1; //数字越小，越优先
    }

}
