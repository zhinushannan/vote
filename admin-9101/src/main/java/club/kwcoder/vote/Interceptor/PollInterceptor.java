package club.kwcoder.vote.Interceptor;

import club.kwcoder.vote.dataobject.VoteUserDO;
import club.kwcoder.vote.dataobject.VoteUserDOExample;
import club.kwcoder.vote.mapper.generate.VoteUserMapper;
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
public class PollInterceptor implements HandlerInterceptor {

    @Autowired
    private VoteUserMapper voteUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String voteIdStr = request.getParameter("voteId");
        if (StrUtil.isBlank(voteIdStr)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        int voteId = Integer.parseInt(voteIdStr);
        int userId = AuthorizationUtils.getUserId(request);

        VoteUserDOExample voteUserDOExample = new VoteUserDOExample();
        voteUserDOExample.createCriteria()
                .andVoteIdEqualTo(voteId)
                .andUserIdEqualTo(userId);
        List<VoteUserDO> voteUserDOS = voteUserMapper.selectByExample(voteUserDOExample);
        if (voteUserDOS.size() > 0) {
            return true;
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }
    }

}
