package com.special.wolf.core.util;

import com.special.wolf.core.exception.EmptyException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Hikaru on 17/8/6.
 */
public final class EmptyUtils {

  public static void emptyException(String val,String message){
    if(StringUtils.isBlank(val)){
      throw new EmptyException(message);
    }
  }

  public static<T> void emptyException(T t,String message){
    if(t == null){
      throw new EmptyException(message);
    }
  }

  public static<T> void emptyException(T [] t,String message){
    if(ArrayUtils.isEmpty(t)){
      throw new EmptyException(message);
    }
  }

  public static<T> void emptyException(Collection<T> collection,String message){
    if(CollectionUtils.isEmpty(collection)){
      throw new EmptyException(message);
    }
  }

  public static<T,E> void emptyException(Map<T,E> map, String message){
    if(MapUtils.isEmpty(map)){
      throw new EmptyException(message);
    }
  }



}
