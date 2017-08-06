package com.special.wolf.core.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.special.wolf.core.RequestCoreTest;
import com.special.wolf.core.exception.ErrorCode;
import com.special.wolf.core.web.vo.User;
import java.util.Map;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * Created by Hikaru on 17/8/4.
 */
public class ReuqestCoreGetTest extends RequestCoreTest {

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamRequest(String, int)}.
   */
  @Test
  public void getParamRequest() throws Exception {
    this.mockMvc.perform(get("/getParamRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamMapRequest(Map)}.
   */
  @Test
  public void getParamMapRequest() throws Exception {
    this.mockMvc.perform(get("/getParamMapRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is("30"))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamBeanpRequest(User)}.
   */
  @Test
  public void getParamBeanpRequest() throws Exception {
    this.mockMvc.perform(get("/getParamBeanpRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(-1)))
        .andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamRequestNo(String, int)}.
   */
  @Test
  public void getParamRequestNo() throws Exception {
    this.mockMvc.perform(get("/getParamRequestNo").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamMapRequestNo(Map)}.
   */
  @Test()
  public void getParamMapRequestNo() throws Exception {
    this.mockMvc.perform(get("/getParamMapRequestNo").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(ErrorCode.EMPTY_ERROR_CODE.errorCode())))
        .andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamBeanpRequestNo(User)}.
   */
  @Test
  public void getParamBeanpRequestNo() throws Exception {
    this.mockMvc.perform(get("/getParamBeanpRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(-1)))
        .andReturn();
  }


  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#getParamBeanRequestModelAttribute(User)}.
   */
  @Test
  public void getParamBeanRequestModelAttribute() throws Exception {
    this.mockMvc.perform(get("/getParamBeanRequestModelAttribute").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

}
