package com.special.wolf.core.web.controller;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.special.wolf.core.result.BaseResult;
import com.special.wolf.core.util.date.DateStyle;
import com.special.wolf.core.web.vo.Work;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hikaru on 17/8/6.
 */
@RestController
public class TimeController {

  @RequestMapping(value = "/getWorkTime", method = RequestMethod.GET)
  public BaseResult getWorkTime() throws ParseException {
    Work work = new Work();
    work.setContent("站立会议");
    work.setWorkTime(DateUtils.parseDate("2017-04-11 12:33:33", DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
    return BaseResult.ok(work);
  }

  @RequestMapping(value = "/postWorkTime", method = RequestMethod.POST)
  public BaseResult postWorkTime(@RequestBody Work work) {
    return BaseResult.ok(work);
  }

  @RequestMapping(value = "/postParamWorkTime", method = RequestMethod.POST)
  public BaseResult postParamWorkTime(@ModelAttribute Work work) {
    return BaseResult.ok(work);
  }

}
