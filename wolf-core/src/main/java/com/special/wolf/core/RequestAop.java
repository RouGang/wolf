/**
 * Project Name:common-component
 * File Name:RequestWrapper.java
 * Package Name:com.huinong.truffle.component.base.component.request
 * Date:2017年5月11日下午2:47:12
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
 */
package com.special.wolf.core;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ClassName:RequestWrapperAOP 请求体对象封装 接口耗时统计 异常统一处理 Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月11日 下午2:47:12 <br/>
 * @author lch
 * @version
 * @since JDK 1.6
 * @see
 */
//@Aspect
@Component
@Slf4j
public class RequestAop
{

  // 注意：所有web.controller目录下的控制器
  @Pointcut("execution(public * com.special..web.controller..*.*(..))")
  public void requestWrapper()
  {
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    //用户ip信息
  }

  @Around("requestWrapper()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable
  {
    long start = System.currentTimeMillis();
    try {
      Object result = joinPoint.proceed();
      long end = System.currentTimeMillis();
      log.info("+++++around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
      return result;

    } catch (Throwable e) {
      long end = System.currentTimeMillis();
      log.error("+++++around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
      throw e;
    }

  }





  private String getBodyJsonStr(HttpServletRequest request)
  {
    BufferedReader reader = null;
    StringWriter sw = new StringWriter();
    try {
      reader = request.getReader();
      String line = null;
      while (null != (line = reader.readLine())) {
        sw.write(line);
      }
      return sw.toString();
    } catch (Throwable e) {
      // 流被其它框架读取
      log.info("read stream from request error:" + e);
      return null;
    } finally {
      try {
        reader.close();
      } catch (Throwable e1) {
        reader = null;
      }
      try {
        sw.close();
      } catch (IOException e) {
        sw = null;
      }
    }
  }


}
