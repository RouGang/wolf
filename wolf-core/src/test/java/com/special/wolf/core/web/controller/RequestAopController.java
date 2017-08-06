package com.special.wolf.core.web.controller;

import com.special.wolf.core.util.EmptyUtils;
import com.special.wolf.core.result.BaseResult;
import com.special.wolf.core.web.vo.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Hikaru on 17/8/4.
 */
@RestController
@Slf4j
public class RequestAopController {

  /**
   * get
   **/
  @RequestMapping(value = "/getParamRequest", method = RequestMethod.GET)
  public BaseResult getParamRequest(@RequestParam("name") String name,
      @RequestParam("age") int age) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name);
    map.put("age", age);
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/getParamMapRequest", method = RequestMethod.GET)
  public BaseResult getParamMapRequest(@RequestParam Map<String, Object> map) {
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/getParamBeanpRequest", method = RequestMethod.GET)
  public BaseResult getParamBeanpRequest(@RequestParam User user) {
    return BaseResult.ok(user);
  }

  @RequestMapping(value = "/getParamRequestNo", method = RequestMethod.GET)
  public BaseResult getParamRequestNo(String name, int age) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name);
    map.put("age", age);
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/getParamMapRequestNo", method = RequestMethod.GET)
  public BaseResult getParamMapRequestNo(Map<String, Object> map) {
    EmptyUtils.emptyException(map, "map内容为空");
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/getParamBeanpRequestNo", method = RequestMethod.GET)
  public BaseResult getParamBeanpRequestNo(User user) {
    return BaseResult.ok(user);
  }

  @RequestMapping(value = "/getParamBeanRequestModelAttribute", method = RequestMethod.GET)
  public BaseResult getParamBeanRequestModelAttribute(@ModelAttribute User user) {
    return BaseResult.ok(user);
  }

  /** get **/

  /**
   * post
   **/

  @RequestMapping(value = "/postParamRequest", method = RequestMethod.POST)
  public BaseResult postParamRequest(@RequestParam("name") String name,
      @RequestParam("age") int age) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name);
    map.put("age", age);
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamMapRequest", method = RequestMethod.POST)
  public BaseResult postParamMapRequest(@RequestParam Map<String, Object> map) {
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamBeanpRequest", method = RequestMethod.POST)
  public BaseResult postParamBeanpRequest(@RequestParam User user) {
    return BaseResult.ok(user);
  }

  @RequestMapping(value = "/postParamRequestNo", method = RequestMethod.POST)
  public BaseResult postParamRequestNo(String name, int age) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name);
    map.put("age", age);
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamMapRequestNo", method = RequestMethod.POST)
  public BaseResult postParamMapRequestNo(Map<String, Object> map) {
    EmptyUtils.emptyException(map, "map为空");
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamBeanpRequestNo", method = RequestMethod.POST)
  public BaseResult postParamBeanpRequestNo(User user) {
    return BaseResult.ok(user);
  }


  @RequestMapping(value = "/postParamBeanRequestModelAttribute", method = RequestMethod.POST)
  public BaseResult postParamBeanRequestModelAttribute(@ModelAttribute User user) {
    return BaseResult.ok(user);
  }

  /** post **/

  /**
   * post json stream    {"name":"黄柔刚"}
   **/

  @RequestMapping(value = "/postSingleParamRequestStream", method = RequestMethod.POST)
  public BaseResult postSingleParamRequestStream(@RequestBody String name) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name);
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamMapRequestStream", method = RequestMethod.POST)
  public BaseResult postParamMapRequestStream(@RequestBody Map<String, Object> map) {
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamBeanpRequestStream", method = RequestMethod.POST)
  public BaseResult postParamBeanpRequestStream(@RequestBody User user) {
    return BaseResult.ok(user);
  }

  @RequestMapping(value = "/postParamRequestNoStream", method = RequestMethod.POST)
  public BaseResult postParamRequestNoStream(String name) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name);
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamMapRequestNoStream", method = RequestMethod.POST)
  public BaseResult postParamMapRequestNoStream(Map<String, Object> map) {
    return BaseResult.ok(map);
  }

  @RequestMapping(value = "/postParamBeanpRequestNoStream", method = RequestMethod.POST)
  public BaseResult postParamBeanpRequestNoStream(User user) {
    return BaseResult.ok(user);
  }

  /** post json stream **/


  /**
   * 上传文件流
   **/

  @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
  public BaseResult singleUpload(@RequestParam("file") MultipartFile file,
      @RequestParam String param) {
    log.info("file name:", file.getOriginalFilename());
    return BaseResult.ok(param);
  }

  @RequestMapping(value = "/batchUpload", method = RequestMethod.POST)
  public BaseResult batchUpload(HttpServletRequest request, @RequestParam String param) {
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
    EmptyUtils.emptyException(files, "文件列表不能为空");
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("param", param);
    map.put("size", files.size());
    return BaseResult.ok(map);
  }

  /** 上传文件流 **/


  /**
   * 下载文件
   **/
  @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
  public BaseResult downLoad(HttpServletResponse response) {
    try {

      InputStream is = this.getClass().getResourceAsStream("/singleUpload1.txt");
      // copy it to response's OutputStream
      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
      response.setHeader("Content-Disposition",
          "attachment; filename=" + "singleUpload1.txt");
      response.flushBuffer();
    } catch (IOException ex) {
      log.info("Error writing file to output stream. Filename was '{}'", "singleUpload1", ex);
      throw new RuntimeException("IOError writing file to output stream");
    }
    return null;
  }




}
