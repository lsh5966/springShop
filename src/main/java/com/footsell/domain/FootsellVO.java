package com.footsell.domain;

import java.util.Date;
import lombok.Data;

@Data
public class FootsellVO extends ReplyVO {

  private int bno;

  private String title;

  private String content;

  private String writer;

  private Date regDate;

  private int viewCnt;

  private int reply_Cnt;

}
