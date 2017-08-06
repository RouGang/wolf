package com.special.wolf.core.exception;

/**
 * Created by Hikaru on 17/8/6.
 */
public class EmptyException extends RuntimeException {

  private String msg;
  private int code = -1;


  public EmptyException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public EmptyException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public EmptyException(String msg, int code) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public EmptyException(String msg, int code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

  public String getMsg() {
    return this.msg;
  }



  public int getCode() {
    return this.code;
  }


}
