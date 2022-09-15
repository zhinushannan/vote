package club.kwcoder.vote.Interceptor;

import club.kwcoder.vote.config.RequestWrapper;
import club.kwcoder.vote.dataobject.CandidateUserDO;
import club.kwcoder.vote.dataobject.CandidateUserDOExample;
import club.kwcoder.vote.mapper.generate.CandidateUserMapper;
import club.kwcoder.vote.util.AuthorizationUtils;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;

@Component
public class CandidateInterceptor implements HandlerInterceptor {

    @Autowired
    private CandidateUserMapper candidateUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String candidateIdStr = null;
        if (request.getMethod().equals(HttpMethod.GET)) {
            candidateIdStr = request.getParameter("candidateId");
        } else if (request.getMethod().equals(HttpMethod.POST)) {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String body = requestWrapper.getBody();
            Integer candidateId = JSONUtil.parseObj(body).get("candidateId", Integer.class);
            candidateIdStr = candidateId + "";
        }

        if (StrUtil.isBlank(candidateIdStr)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        int candidateId = Integer.parseInt(candidateIdStr);
        int userId = AuthorizationUtils.getUserId(request);
        CandidateUserDOExample candidateUserDOExample = new CandidateUserDOExample();
        candidateUserDOExample.createCriteria()
                .andCandidateIdEqualTo(candidateId)
                .andUserIdEqualTo(userId);

        List<CandidateUserDO> candidateUserDOS = candidateUserMapper.selectByExample(candidateUserDOExample);

        if (candidateUserDOS.size() > 0) {
            return true;
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }

    }
}
