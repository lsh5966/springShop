package com.footsell.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.footsell.domain.ReplyVO;

@Repository
public class ReplyDAOlmpl implements ReplyDAO {
  @Inject
  private SqlSession sql;

  private static String namespace = "com.footsell.mappers.reply";

  public List<ReplyVO> list(int bno) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".replyList", Integer.valueOf(bno));
  }

  public void write(ReplyVO vo) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".replyWrite", vo);
  }

  public void modify(ReplyVO vo) throws Exception {
    this.sql.update(String.valueOf(namespace) + ".replyModify", vo);
  }

  public void delete(ReplyVO vo) throws Exception {
    System.out.println("vo 파라미터 " + vo.toString());
    this.sql.delete(String.valueOf(namespace) + ".replyDelete", vo);
  }

  public ReplyVO replySelect(ReplyVO vo) throws Exception {
    return (ReplyVO) this.sql.selectOne(String.valueOf(namespace) + ".replySelect", vo);
  }

  public String getPassword(ReplyVO vo) throws Exception {
    return (String) this.sql.selectOne(String.valueOf(namespace) + ".getPassword", vo);
  }
}
