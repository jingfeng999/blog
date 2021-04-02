package com.jingfeng999.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Auther : jingfeng999
 * @Date : 2021/4/2 14:35
 * @Description: blog
 * @Version: 1.0
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "execution(* com.jingfeng999.blog.web.*.*(..))")
    public void log(){}

    @Before(value = "log()")
    public void doBefore(JoinPoint jp){
        //获取Request对象
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //从Request对象中获取用户请求的url和用户ip
        String url = request.getRequestURL().toString();//url
        String ip = request.getRemoteAddr();//ip
        //从JoinPoint中获取被调用方法的信息
        String classMethod = jp.getSignature().getDeclaringTypeName() + jp.getSignature().getName();//类名.方法名
        Object[] args = jp.getArgs();//方法参数列表
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}" , requestLog);
    }

//    @After("log()")
//    public void doAfter(){
//        logger.info("==============doAfter========================");
//    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result : {}" , result);
    }


    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
