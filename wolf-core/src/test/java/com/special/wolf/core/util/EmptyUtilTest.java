package com.special.wolf.core.util;

import com.special.wolf.core.exception.EmptyException;
import com.special.wolf.core.web.vo.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Created by Hikaru on 17/8/6.
 */

public class EmptyUtilTest {

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(String, String)}.
   */
  @Test(expected = EmptyException.class)
  public void StringBlankEmptyException() {
    String val = "";
    EmptyUtils.emptyException(val, "空");

  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(String, String)}.
   */
  @Test(expected = EmptyException.class)
  public void StringEmptyException() {
    String val = null;
    EmptyUtils.emptyException(val, "空");
  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Object, String)}.
   */
  @Test(expected = EmptyException.class)
  public void BeanEmptyException() {
    User user = null;
    EmptyUtils.emptyException(user, "空");
  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Object[], String)}.
   */
  @Test(expected = EmptyException.class)
  public void ArrayEmptyException() {
    String[] str = new String[]{};
    EmptyUtils.emptyException(str, "空");
  }


  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Collection, String)}.
   */
  @Test(expected = EmptyException.class)
  public void ListEmptyException() {
    List list = new ArrayList();
    EmptyUtils.emptyException(list, "空");
  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Map, String)}.
   */
  @Test(expected = EmptyException.class)
  public void MapEmptyException() {
    Map<String, Object> map = new HashMap<String, Object>();
    EmptyUtils.emptyException(map, "空");
  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Object, String)}.
   */
  @Test(expected = EmptyException.class)
  public void IntegerEmptyException() {
    Integer j = null;
    EmptyUtils.emptyException(j, "空");
  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Object, String)}.
   */
  @Test(expected = EmptyException.class)
  public void LongEmptyException() {
    Long j = null;
    EmptyUtils.emptyException(j, "空");
  }

  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Collection, String)}.
   */
  @Test(expected = EmptyException.class)
  public void ListNullEmptyException() {
    List list = null;
    EmptyUtils.emptyException(list, "空");
  }


  /**
   * Test method for {@link com.special.wolf.core.util.EmptyUtils#emptyException(Map, String)}.
   */
  @Test(expected = EmptyException.class)
  public void MapNullEmptyException() {
    Map<String, Object> map = null;
    EmptyUtils.emptyException(map, "空");
  }

}
