package com.footsell.service;

import javax.servlet.http.HttpSession;
import com.footsell.domain.MemberVO;

public interface MemberService {
  void signup(MemberVO paramMemberVO) throws Exception;

  MemberVO signin(MemberVO paramMemberVO) throws Exception;

  void signout(HttpSession paramHttpSession) throws Exception;
}
