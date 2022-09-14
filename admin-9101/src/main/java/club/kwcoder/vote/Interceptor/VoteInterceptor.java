package club.kwcoder.vote.Interceptor;


import club.kwcoder.vote.dataobject.VoteUserDO;
import club.kwcoder.vote.dataobject.VoteUserDOExample;
import club.kwcoder.vote.mapper.generate.VoteUserMapper;
import club.kwcoder.vote.util.AuthorizationUtils;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class VoteInterceptor implements HandlerInterceptor {

    private final List<String> needCheck;
    @Autowired
    private VoteUserMapper voteUserMapper;

    {
        needCheck = new ArrayList<>();
        // 需要从对象中获取 candidateid
        needCheck.add("/candidate/update");

        needCheck.add("/candidate/delete");
        needCheck.add("/candidate/recovery");

        needCheck.add("/poll/info");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jwt = request.getHeader(AuthorizationUtils.header);
        Integer userId = JWTUtil.parseToken(jwt).getPayloads().get("id", Integer.class);

        int voteId = Integer.parseInt(request.getParameter("voteId"));
        VoteUserDOExample voteUserDOExample = new VoteUserDOExample();
        voteUserDOExample.createCriteria()
                .andVoteIdEqualTo(voteId)
                .andUserIdEqualTo(userId);
        List<VoteUserDO> voteUserDOS = voteUserMapper.selectByExample(voteUserDOExample);

        if (voteUserDOS.size() > 0) {
            return true;
        } else {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
            return false;
        }
    }

}
