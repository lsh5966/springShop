package com.footsell.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ReplyVO {
  private int rno;

  private int bno;

  private String writer;

  private String content;

  private Date regDate;

  private int reply_count;

  private String password;
}
