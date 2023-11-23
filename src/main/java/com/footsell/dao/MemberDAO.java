package com.footsell.dao;

import com.footsell.domain.MemberVO;

public interface MemberDAO {
  void signup(MemberVO paramMemberVO) throws Exception;

  MemberVO signin(MemberVO paramMemberVO) throws Exception;
}
