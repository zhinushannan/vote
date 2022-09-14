package club.kwcoder.vote.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义一个切点
     */
    @Pointcut("execution(public * club.kwcoder.vote.controller.*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 日志编号
        MDC.put("UUID", UUID.randomUUID().toString().split("-")[0]);

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的类
        Class cls = signature.getDeclaringType();

        String classBusiness = (String) cls.getDeclaredField("BUSINESS").get(null);
        String methodBusiness = (String) cls.getDeclaredField(methodName).get(null);

        String phone = "";
        if (request.getRequestURL().toString().contains("/login") || request.getRequestURL().toString().contains("/register") ) {

        } else {
            JWT jwt = JWTUtil.parseToken(request.getHeader("authorization"));
            JSONObject payloads = jwt.getPayloads();
            phone = payloads.get("phe", String.class);
        }


        // 打印请求信息
        LOG.info("------------- 【{}】-【{}】【{}】开始 -------------", phone, classBusiness, methodBusiness);
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), signature.getName());
        LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        LOG.info("请求参数: {}", JSONUtil.toJsonStr(arguments));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        LOG.info("返回结果: {}", JSONUtil.toJsonStr(result));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}
