package com.footsell.dao;

import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.footsell.domain.FootsellVO;

@Repository
public class FootsellDAOlmpl implements FootsellDAO {
  @Inject
  private SqlSession sql;

  private static String namespace = "com.footsell.mappers.board";

  public List<FootsellVO> list() throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".list");
  }

  public void write(FootsellVO vo) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".write", vo);
  }

  public FootsellVO view(int bno) throws Exception {
    return (FootsellVO) this.sql.selectOne(String.valueOf(namespace) + ".view",
        Integer.valueOf(bno));
  }

  public void modify(FootsellVO vo) throws Exception {
    this.sql.update(String.valueOf(namespace) + ".modify", vo);
  }

  public void delete(int bno) throws Exception {
    this.sql.delete(String.valueOf(namespace) + ".delete", Integer.valueOf(bno));
  }

  public int count() throws Exception {
    return ((Integer) this.sql.selectOne(String.valueOf(namespace) + ".count")).intValue();
  }

  public List<FootsellVO> listPageSearch(int displayPost, int postNum, String searchType,
      String keyword) throws Exception {
    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("displayPost", Integer.valueOf(displayPost));
    data.put("postNum", Integer.valueOf(postNum));
    data.put("searchType", searchType);
    data.put("keyword", keyword);
    return this.sql.selectList(String.valueOf(namespace) + ".listPageSearch", data);
  }

  public int searchCount(String searchType, String keyword) throws Exception {
    HashMap<Object, Object> data = new HashMap<Object, Object>();
    data.put("searchType", searchType);
    data.put("keyword", keyword);
    return ((Integer) this.sql.selectOne(String.valueOf(namespace) + ".searchCount", data))
        .intValue();
  }

  public int viewCnt(Integer bno) throws Exception {
    return this.sql.update(String.valueOf(namespace) + ".viewCnt", bno);
  }

  public void updateReplyCount(int bno) throws Exception {
    this.sql.update(String.valueOf(namespace) + ".updateReplyCount", Integer.valueOf(bno));
  }
}
