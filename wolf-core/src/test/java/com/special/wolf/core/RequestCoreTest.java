package com.special.wolf.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Hikaru on 17/8/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RequestCoreTest {

  public static final String ENCODING_UTF8 = "UTF-8";

  @Autowired
  private WebApplicationContext webApplicationContext;

  public MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void contextLoads() {
    log.info("hello world");
  }


}
