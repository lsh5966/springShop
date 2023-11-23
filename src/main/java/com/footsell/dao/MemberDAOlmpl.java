package com.footsell.dao;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.footsell.domain.MemberVO;

@Repository
public class MemberDAOlmpl implements MemberDAO {
  @Inject
  private SqlSession sql;

  private static String namespace = "com.footsell.mappers.memberMapper";

  public void signup(MemberVO vo) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".signup", vo);
  }

  public MemberVO signin(MemberVO vo) throws Exception {
    return (MemberVO) this.sql.selectOne(String.valueOf(namespace) + ".signin", vo);
  }
}
