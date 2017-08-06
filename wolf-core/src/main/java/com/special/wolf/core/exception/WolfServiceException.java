package com.special.wolf.core.exception;

/**
 * Created by Hikaru on 17/8/4.
 */
public class WolfServiceException extends RuntimeException {

  private static final long serialVersionUID = -7012555312286012810L;
  private String msg;
  private int code = -1;


  public WolfServiceException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public WolfServiceException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public WolfServiceException(String msg, int code) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public WolfServiceException(String msg, int code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

  public String getMsg() {
    return this.msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

}
