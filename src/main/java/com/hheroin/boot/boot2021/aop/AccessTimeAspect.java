package com.hheroin.boot.boot2021.aop;

import com.hheroin.boot.boot2021.annotation.AccessTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalTime;

@Aspect
@Slf4j
@Component
public class AccessTimeAspect {

    @Pointcut("@annotation(com.hheroin.boot.boot2021.annotation.AccessTime)")
    public void acPointcut() {
    }


    @Around("acPointcut()")
    public Object accessTimeLog(ProceedingJoinPoint pjp) throws Throwable {
        long begin = LocalTime.now().toNanoOfDay();
        log.info("start:------ {}", begin);
        Object result = pjp.proceed();
        long end = LocalTime.now().toNanoOfDay();
        log.info("end:------ {}", end);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        String methodName = method.getName();
        String className = pjp.getTarget().getClass().getName();
        Object[] args = pjp.getArgs();
        log.info("className:{},methodName:{},parameters:{}",className,methodName,args);

        AccessTime annotation = method.getAnnotation(AccessTime.class);
        String value = annotation.value();
        if (value != null) {
            switch (value) {
                case "second":
                    log.info("耗时:{}秒",(end-begin)/1000_000_000);
                    break;
                case "nano":
                    log.info("耗时:{}纳秒",end-begin);
                    break;
                default:
                    log.error("不支持的类型:{}",value);
                    break;
            }
        }


        return result;

    }
}
