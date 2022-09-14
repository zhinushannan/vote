package club.kwcoder.vote.Interceptor;

import club.kwcoder.vote.dataobject.CandidateUserDO;
import club.kwcoder.vote.dataobject.CandidateUserDOExample;
import club.kwcoder.vote.mapper.generate.CandidateUserMapper;
import club.kwcoder.vote.util.AuthorizationUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class CandidateInterceptor implements HandlerInterceptor {

    @Autowired
    private CandidateUserMapper candidateUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String candidateIdStr = request.getParameter("candidateId");
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
