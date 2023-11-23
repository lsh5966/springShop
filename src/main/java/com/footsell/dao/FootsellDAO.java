package com.footsell.dao;

import java.util.List;
import com.footsell.domain.FootsellVO;

public interface FootsellDAO {
  List<FootsellVO> list() throws Exception;

  void write(FootsellVO paramFootsellVO) throws Exception;

  FootsellVO view(int paramInt) throws Exception;

  void modify(FootsellVO paramFootsellVO) throws Exception;

  void delete(int paramInt) throws Exception;

  int count() throws Exception;

  List<FootsellVO> listPageSearch(int paramInt1, int paramInt2, String paramString1,
      String paramString2) throws Exception;

  int searchCount(String paramString1, String paramString2) throws Exception;

  int viewCnt(Integer paramInteger) throws Exception;

  void updateReplyCount(int paramInt) throws Exception;
}
