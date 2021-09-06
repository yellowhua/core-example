package com.hh.core.basic.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author huanghua
 * @date 2020/12/2
 */
@Aspect
@Component
@Slf4j
public class AopDemoAspect {

    /**
     * 切点
     */
    @Pointcut("execution(public * com.hh.core.basic.aop.AopDemo.*(..)) ")
    public void pointcut() {}

    /**
     * 环绕通知
     * @param jp jp
     * @return 转码结果
     */
    @Around(value = "pointcut()")
    public Object transcodeException(ProceedingJoinPoint jp) {
        // 如果原始转码数据为空，直接返回空
        Object args = jp.getArgs()[0];
        if (null == args) { return null; }
        Object result;
        try {
            result = jp.proceed();
        } catch (Throwable e) {
            // 如果发生异常，那么也返回原始转码数据
            log.error("transcode error: {}", e.getMessage());
            return args;
        }
        return result;
    }

}
