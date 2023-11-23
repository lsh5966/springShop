package com.footsell.service;

import java.util.List;
import com.footsell.domain.FootsellVO;

public interface Footsellservice {


  public List<FootsellVO> list() throws Exception;

  public void write(FootsellVO paramFootsellVO) throws Exception;

  public FootsellVO view(int paramInt) throws Exception;

  public void modify(FootsellVO paramFootsellVO) throws Exception;

  public void delete(int paramInt) throws Exception;

  public int count() throws Exception;

  public List<FootsellVO> listPageSearch(int displayPost, int postNum, String searchType,
      String keyword) throws Exception;

  public int searchCount(String paramString1, String paramString2) throws Exception;

  public int viewCnt(Integer paramInteger) throws Exception;

  public void updateReplyCount(int paramInt) throws Exception;
}
