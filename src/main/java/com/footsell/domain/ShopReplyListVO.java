package com.footsell.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ShopReplyListVO {
  private int gdsNum;

  private String userId;

  private int repNum;

  private String repCon;

  private Date repDate;

  private String userName;

  private int repParent;
}
