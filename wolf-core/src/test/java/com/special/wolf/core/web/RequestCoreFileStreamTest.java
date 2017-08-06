package com.special.wolf.core.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.special.wolf.core.RequestCoreTest;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Hikaru on 17/8/4.
 */
public class RequestCoreFileStreamTest extends RequestCoreTest {

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#singleUpload(MultipartFile,
   * String)}.
   */
  @Test
  public void singleUpload() throws Exception {
    InputStream inputStream = this.getClass().getResourceAsStream("/singleUpload1.txt");
    MockMultipartFile multipartFile = new MockMultipartFile("file", inputStream);
    this.mockMvc.perform(fileUpload("/singleUpload").
        file(multipartFile).param("param", "265001916915724"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.msg", is("265001916915724")))
        .andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#batchUpload(HttpServletRequest, String)}.
   */
  @Test
  public void batchUpload() throws Exception {
    InputStream inputStream = this.getClass().getResourceAsStream("/singleUpload1.txt");
    InputStream inputStream2 = this.getClass().getResourceAsStream("/singleUpload2.txt");
    MockMultipartFile multipartFile = new MockMultipartFile("file", inputStream);
    MockMultipartFile multipartFile2 = new MockMultipartFile("file", inputStream2);
    this.mockMvc.perform(fileUpload("/batchUpload")
        .file(multipartFile)
        .file(multipartFile2)
        .param("param", "265001916915724"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(0)))
        .andExpect(jsonPath("$.data.param", is("265001916915724")))
        .andExpect(jsonPath("$.data.size", is(2)))
        .andReturn();
  }

  /**
   * Test method for {@link com.special.wolf.core.web.controller.RequestAopController#downLoad(HttpServletResponse)}.
   */
  @Test
  public void downLoad()throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/downLoad"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Disposition",
            "attachment; filename=" + "singleUpload1.txt"))

        .andReturn();
  }
}
