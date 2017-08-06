package com.special.wolf.core.exception.advice;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.special.wolf.core.exception.EmptyException;
import com.special.wolf.core.exception.ErrorCode;
import com.special.wolf.core.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Hikaru on 17/8/6.
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {

  @ExceptionHandler(Throwable.class)
  public BaseResult handleException(Throwable e){
    log.error("error",e);
    return BaseResult.error();
  }

  @ExceptionHandler(EmptyException.class)
  public BaseResult handleException(EmptyException e){
    log.error("error",e);
    return BaseResult.error(ErrorCode.EMPTY_ERROR_CODE.errorCode(),e.getMsg());
  }

}
