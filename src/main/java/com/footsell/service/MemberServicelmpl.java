package com.footsell.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.footsell.dao.MemberDAO;
import com.footsell.domain.MemberVO;

@Service
public class MemberServicelmpl implements MemberService {
  @Inject
  private MemberDAO dao;

  public void signup(MemberVO vo) throws Exception {
    this.dao.signup(vo);
  }

  public MemberVO signin(MemberVO vo) throws Exception {
    return this.dao.signin(vo);
  }

  public void signout(HttpSession session) throws Exception {
    session.invalidate();
  }
}
