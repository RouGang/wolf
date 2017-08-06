package com.special.wolf.core.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.special.wolf.core.RequestCoreTest;
import com.special.wolf.core.result.BaseResult;
import com.special.wolf.core.util.JsonUtil;
import com.special.wolf.core.util.date.DateStyle;
import com.special.wolf.core.web.vo.Work;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hikaru on 17/8/6.
 */
public class TimeControllerTest extends RequestCoreTest {

  @Test
  public void getWorkTime() throws Exception {
    this.mockMvc.perform(get("/getWorkTime")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.workTime", is(1491885213000l)))
        .andReturn();
  }

  @Test
  public void postWorkTime() throws Exception {
    Work work = new Work();
    work.setContent("站立会议");
    work.setWorkTime(DateUtils.parseDate("2017-04-11 12:33:33", DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
    this.mockMvc.perform(post("/postWorkTime").
        content(JsonUtil.obj2string(work).getBytes(ENCODING_UTF8))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.workTime", is(1491885213000l)))
        .andReturn();
  }

  @Test
  public void postParamWorkTime() throws Exception{
    this.mockMvc.perform(post("/postParamWorkTime")
        .param("content","站立会议")
        .param("workTime","2017-04-11 12:33:33"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.workTime", is(1491885213000l)))
        .andReturn();
  }


}
