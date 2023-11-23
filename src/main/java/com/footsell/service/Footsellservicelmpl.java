package com.footsell.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.footsell.dao.FootsellDAO;
import com.footsell.domain.FootsellVO;

@Service
public class Footsellservicelmpl implements Footsellservice {
  @Inject
  private FootsellDAO dao;

  public List<FootsellVO> list() throws Exception {
    return this.dao.list();
  }

  public void write(FootsellVO vo) throws Exception {
    this.dao.write(vo);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public FootsellVO view(int bno) throws Exception {
    this.dao.viewCnt(Integer.valueOf(bno));
    this.dao.updateReplyCount(bno);
    return this.dao.view(bno);
  }

  public void modify(FootsellVO vo) throws Exception {
    this.dao.modify(vo);
  }

  public void delete(int bno) throws Exception {
    this.dao.delete(bno);
  }

  public int count() throws Exception {
    return this.dao.count();
  }

  public List<FootsellVO> listPageSearch(int displayPost, int postNum, String searchType,
      String keyword) throws Exception {
    return this.dao.listPageSearch(displayPost, postNum, searchType, keyword);
  }

  public int searchCount(String searchType, String keyword) throws Exception {
    return this.dao.searchCount(searchType, keyword);
  }

  public int viewCnt(Integer bno) throws Exception {
    return this.dao.viewCnt(bno);
  }

  public void updateReplyCount(int bno) throws Exception {
    this.dao.updateReplyCount(bno);
  }
}
