package com.special.wolf.core.web.vo;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Hikaru on 17/8/6.
 */

@Data
public class Work {

  private String content;
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date workTime;

}
