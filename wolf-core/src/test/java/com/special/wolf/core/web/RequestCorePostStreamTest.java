package com.special.wolf.core.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.special.wolf.core.RequestCoreTest;
import com.special.wolf.core.util.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * Created by Hikaru on 17/8/4.
 */
public class RequestCorePostStreamTest extends RequestCoreTest {

  @Test
  public void postSingleParamRequestStream() throws Exception {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name","黄柔刚");
    this.mockMvc.perform(post("/postSingleParamRequestStream").
        content(JsonUtil.obj2string(map).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", not("黄柔刚")))
        .andReturn();
  }

  @Test
  public void postParamMapRequestStream() throws Exception {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name","黄柔刚");
    map.put("age",30);
    this.mockMvc.perform(post("/postParamMapRequestStream").
        content(JsonUtil.obj2string(map).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  @Test
  public void postParamBeanpRequestStream() throws Exception{
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name","黄柔刚");
    map.put("age",30);
    this.mockMvc.perform(post("/postParamBeanpRequestStream").
        content(JsonUtil.obj2string(map).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is("黄柔刚")))
        .andExpect(jsonPath("$.data.age", is(30))).andReturn();
  }

  @Test
  public void postParamRequestNoStream() throws Exception {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name","黄柔刚");
    map.put("age",30);
    this.mockMvc.perform(post("/postParamRequestNoStream").
        content(JsonUtil.obj2string(map).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is(nullValue())))
        .andReturn();
  }

  @Test
  public void postParamMapRequestNoStream() throws Exception {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name","黄柔刚");
    map.put("age",30);
    this.mockMvc.perform(post("/postParamMapRequestNoStream").
        content(JsonUtil.obj2string(map).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andReturn();
  }

  @Test
  public void postParamBeanpRequestNoStream() throws Exception{
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name","黄柔刚");
    map.put("age",30);
    this.mockMvc.perform(post("/postParamBeanpRequestNoStream").
        content(JsonUtil.obj2string(map).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.name", is(nullValue())))
        .andExpect(jsonPath("$.data.age", is(nullValue()))).andReturn();
  }

}
