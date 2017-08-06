package com.special.wolf.core.result;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Hikaru on 17/8/4.
 */
public class BaseResult extends HashMap<String, Object> {

  private static final int ERROR_CODE = -1;
  private static final String DATA_NAME = "data";
  private static final String CODE_NAME = "code";
  private static final String MSG_NAME = "msg";
  private static final int SUCESS_CDOE = 0;

  public BaseResult() {
    put(CODE_NAME, SUCESS_CDOE);
  }

  public static BaseResult error() {
    return error(ERROR_CODE, "未知异常，请联系管理员");
  }

  public static BaseResult error(String msg) {
    return error(ERROR_CODE, msg);
  }

  public static BaseResult error(int code, String msg) {
    BaseResult r = new BaseResult();
    r.put(CODE_NAME, code);
    r.put(MSG_NAME, msg);
    return r;
  }

  public static BaseResult ok(String msg) {
    BaseResult r = new BaseResult();
    r.put(MSG_NAME, msg);
    return r;
  }

  public static <T> BaseResult ok(T t) {
    BaseResult r = new BaseResult();
    r.put(DATA_NAME, t);
    return r;
  }

  public static <T> BaseResult ok(List<T> list) {
    BaseResult r = new BaseResult();
    r.put(DATA_NAME, list);
    return r;
  }

  public static <T> BaseResult ok(T[] t) {
    BaseResult r = new BaseResult();
    r.put(DATA_NAME, t);
    return r;
  }

  public static BaseResult ok() {
    return new BaseResult();
  }

  public BaseResult put(String key, Object value) {
    super.put(key, value);
    return this;
  }

}
