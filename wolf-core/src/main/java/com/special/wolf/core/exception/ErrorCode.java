package com.special.wolf.core.exception;

/**
 * Created by Hikaru on 17/8/6.
 */
public enum ErrorCode {

  EMPTY_ERROR_CODE(40000, "参数不能为空");

  private int errorCode;
  private String msg;

  private ErrorCode(int errorCode, String msg) {
    this.errorCode = errorCode;
    this.msg = msg;
  }

  public String msg() {
    return this.msg;
  }

  public int errorCode() {
    return this.errorCode;
  }

}
