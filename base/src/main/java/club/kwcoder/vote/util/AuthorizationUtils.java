package club.kwcoder.vote.util;

import cn.hutool.jwt.JWTUtil;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationUtils {

    public static final String header = "authorization";

    public static int getUserId(HttpServletRequest request) {
        String jwt = request.getHeader(header);
        return JWTUtil.parseToken(jwt).getPayloads().get("id", Integer.class);
    }

}
