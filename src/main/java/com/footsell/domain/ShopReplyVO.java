package com.footsell.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ShopReplyVO {
  private int gdsNum;

  private String userId;

  private int repNum;

  private String repCon;

  private Date repDate;

  private int repParent;
}
