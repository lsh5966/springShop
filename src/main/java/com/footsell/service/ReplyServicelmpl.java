package com.footsell.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.footsell.dao.ReplyDAO;
import com.footsell.domain.ReplyVO;

@Repository
public class ReplyServicelmpl implements ReplyService {
  @Inject
  private ReplyDAO dao;

  public List<ReplyVO> list(int bno) throws Exception {
    return this.dao.list(bno);
  }

  public void write(ReplyVO vo) throws Exception {
    this.dao.write(vo);
  }

  public void modify(ReplyVO vo) throws Exception {
    this.dao.modify(vo);
  }

  public ReplyVO replySelect(ReplyVO vo) throws Exception {
    return this.dao.replySelect(vo);
  }

  public int deleteWithPassword(ReplyVO vo, String password) throws Exception {
    System.out.println("입력된 비밀번호: " + password);
    String storedPassword = this.dao.getPassword(vo);
    System.out.println("DB에 저장된 비밀번호: " + storedPassword);
    if (!password.equals(storedPassword))
      throw new Exception("비밀번호가 일치하지 않습니다.");
    int resultCnt = 0;
    this.dao.delete(vo);
    resultCnt++;
    return resultCnt;
  }
}
