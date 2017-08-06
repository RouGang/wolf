package com.special.wolf.core.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.special.wolf.core.RequestCoreApplication;
import com.special.wolf.core.RequestCoreTest;
import com.special.wolf.core.exception.ErrorCode;
import com.special.wolf.core.result.BaseResult;
import com.special.wolf.core.web.vo.User;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Hikaru on 17/8/4.
 */
public class RequestCorePostTest extends RequestCoreTest {

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamRequest(String, int)}.
   */
  @Test
  public void postParamRequest() throws Exception {
    this.mockMvc.perform(post("/postParamRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamMapRequest(Map)}.
   */
  @Test
  public void postParamMapRequest() throws Exception {
    this.mockMvc.perform(post("/postParamMapRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is("30"))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamBeanpRequest(User)}.
   */
  @Test
  public void postParamBeanpRequest() throws Exception {
    this.mockMvc.perform(post("/postParamBeanpRequest").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(-1)))
        .andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamRequestNo(String, int)}.
   */
  @Test
  public void postParamRequestNo() throws Exception {
    this.mockMvc.perform(post("/postParamRequestNo").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamMapRequestNo(Map)}.
   */
  @Test
  public void postParamMapRequestNo() throws Exception {
    this.mockMvc.perform(post("/postParamMapRequestNo").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(ErrorCode.EMPTY_ERROR_CODE.errorCode())))
        .andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamBeanpRequestNo(User)}.
   */
  @Test
  public void postParamBeanpRequestNo() throws Exception {
    this.mockMvc.perform(post("/postParamBeanpRequestNo").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#postParamBeanRequestModelAttribute(User)}.
   */
  @Test
  public void postParamBeanRequestModelAttribute() throws Exception {
    this.mockMvc.perform(post("/postParamBeanRequestModelAttribute").param("name", "黄柔刚")
        .param("age", "30").characterEncoding(ENCODING_UTF8)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }



}
