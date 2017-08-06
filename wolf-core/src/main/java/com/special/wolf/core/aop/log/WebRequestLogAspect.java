package com.special.wolf.core.aop.log;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

import com.special.wolf.core.util.IpUtils;
import com.special.wolf.core.util.JsonUtil;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.WebUtils;

/**
 * Created by Hikaru on 17/8/6.
 * 在切入点前的操作，按order的值由小到大执行
 * 在切入点后的操作，按order的值由大到小执行
 *
 * 要求，建议请求，查询请求采用post ，其他操作采用post请求
 * get请求，注解采用@modelAttribute
 * post 统一走json请求，采用@requestBody
 *
 * 注意： 请勿使用path路径变量操作，不方便统计分析。
 * 类注解:
 @Aspect将一个类定义为一个切面类
 @order(i)标记切面类的处理优先级,i值越小,优先级别越高.PS:可以注解类,也能注解到方法上
 方法注解:
 @Pointcut定义一个方法为切点里面的内容为一个表达式,下面详细介绍
 @Before 在切点前执行方法,内容为指定的切点
 @After 在切点后,return前执行,
 @AfterReturning在切入点,return后执行,如果想对某些方法的返回参数进行处理,可以在这操作
 @Around 环绕切点,在进入切点前,跟切点后执行
 @AfterThrowing 在切点后抛出异常进行处理
 @order(i) 标记切点的优先级,i越小,优先级越高
 */
@Aspect
@Component
@Slf4j
public class WebRequestLogAspect {



  private ThreadLocal<OperatorLog> tlocal = new ThreadLocal<OperatorLog>();

  @Pointcut("execution(public * com.special..web.controller..*Controller.*(..))")
  public void webRequestLog() {
  }

  @Before("webRequestLog()")
  public void doBefore(JoinPoint joinPoint) {
    try {

      long beginTime = System.currentTimeMillis();

      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
          .getRequestAttributes();
      HttpServletRequest request = attributes.getRequest();
      String beanName = joinPoint.getSignature().getDeclaringTypeName();
      String methodName = joinPoint.getSignature().getName();
      String uri = request.getRequestURI();
      String remoteAddr = IpUtils.getRemoteAddr(request);
      String method = request.getMethod();
      String params = "";
      String reqContentType = ReqContentType.PARAM.name();
      if (RequestMethod.GET.name().equals(method)) {
        Map<String,Object> map = getQueryParams(request);
        params = JsonUtil.obj2string(map);
      } else {
        String contentType = request.getContentType();
        if(StringUtils.isNotBlank(contentType)){
          if(contentType!=null && contentType.toLowerCase().contains(MediaType.APPLICATION_JSON_VALUE)){
            reqContentType = ReqContentType.JSON.name();
            Object[] paramsArray = joinPoint.getArgs();
            if(ArrayUtils.isNotEmpty(paramsArray)){
              MethodSignature signature = (MethodSignature) joinPoint.getSignature();
              Method methods = signature.getMethod();
              Annotation[][] methodAnnotations = methods.getParameterAnnotations();
              for(int i = 0,len = paramsArray.length;i<len;i++){
                Object parameterValue = paramsArray[i];
                Annotation[] annotationsOnParameter = methodAnnotations[i];
                if(parameterValue!=null && hasRequestBodyAnnotation(annotationsOnParameter)){
                  params = JsonUtil.obj2string(parameterValue);
                  break;
                }
              }
            }
          }else if(MultipartResolutionDelegate.isMultipartRequest(request)) {

          }else{
            Map<String,Object> paramQuerys = new HashMap<>();
            Enumeration<String> keyEnum = request.getParameterNames();
            while (keyEnum.hasMoreElements()) {
              String paramName = (String) keyEnum.nextElement();
              paramQuerys.put(paramName, request.getParameter(paramName));
            }
            params = JsonUtil.obj2string(paramQuerys);
          }
        }else{
          Map<String,Object> paramQuerys = new HashMap<>();
          Enumeration<String> keyEnum = request.getParameterNames();
          while (keyEnum.hasMoreElements()) {
            String paramName = (String) keyEnum.nextElement();
            paramQuerys.put(paramName, request.getParameter(paramName));
          }
          params = JsonUtil.obj2string(paramQuerys);
        }

      }
      if (log.isDebugEnabled()) {
        log.debug("uri=" + uri + "; beanName=" + beanName + "; remoteAddr=" + remoteAddr
            + "; methodName=" + methodName + "; params=" + params);
      }

      OperatorLog optLog = new OperatorLog();
      optLog.setBeanName(beanName);
      optLog.setMethodName(methodName);
      optLog.setParam(params != null ? params.toString() : "");
      optLog.setIp(remoteAddr);
      optLog.setUri(uri);
      optLog.setBegTime(beginTime);
      tlocal.set(optLog);

    } catch (Exception e) {
      log.error("***操作请求日志记录失败doBefore()***", e);
    }
  }


  private boolean hasRequestBodyAnnotation(Annotation... annotations) {
    return Arrays.asList(annotations).stream().
        anyMatch(a -> a.annotationType() == RequestBody.class);
  }

  @AfterReturning(returning = "result", pointcut = "webRequestLog()")
  public void doAfterReturning(Object result) {
    try {
      // 处理完请求，返回内容
      OperatorLog optLog = tlocal.get();
      optLog.setResult(result.toString());
      long timePass = (System.currentTimeMillis() - optLog.getBegTime()) ;
      optLog.setTimePass(timePass);

      System.out.println(
          "请求耗时：" + optLog.getBegTime() + optLog.getUri() + "   **  " + optLog.getParam() + " ** "
              + optLog.getMethodName());
      System.out.println("RESPONSE : " + result);

      log.info(optLog.toString());
    } catch (Exception e) {
      log.error("***操作请求日志记录失败doAfterReturning()***", e);
    }
  }



  private String argsArrayToString(Object[] paramsArray) {
    String params = "";
    if (paramsArray != null && paramsArray.length > 0) {
      for (int i = 0; i < paramsArray.length; i++) {
        params += JsonUtil.obj2string(paramsArray[i]) + " ";
      }
    }
    return params.trim();
  }


  private Map<String, Object> getQueryParams(HttpServletRequest request)
  {
    String queryStr = request.getQueryString();
    if (StringUtils.isNotBlank(queryStr)) {
      Map<String, Object> paramQuerys = new HashMap<String, Object>();
      for (String entry : queryStr.trim().split("&")) {
        if (entry.indexOf("=") > 0)
          try {
            paramQuerys.put(entry.substring(0, entry.indexOf("=")),
                URLDecoder.decode(entry.substring(entry.indexOf("=") + 1), UTF_8));
          } catch (UnsupportedEncodingException e) {
            log.error("getQueryParams from request error:{}", e);
          }
      }
      return paramQuerys;
    }
    return new HashMap<String, Object>();
  }


}
