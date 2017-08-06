package com.special.wolf.core.aop.log;

import lombok.Data;

/**
 * Created by Hikaru on 17/8/6.
 */
@Data
public class OperatorLog {

  private String beanName;
  private String methodName;
  private String uri;
  private Long begTime;
  private Long endTime;
  private Long timePass;
  private String param;
  private String ip;
  private String result;
  private String localIp;
  private String rct;



}
