package com.footsell.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.footsell.domain.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj)
      throws Exception {
    HttpSession session = req.getSession();
    MemberVO member = (MemberVO) session.getAttribute("member");
    if (member == null) {
      res.sendRedirect("/member/signin");
      return false;
    }
    if (member == null || member.getVerify() != 9) {
      res.sendRedirect("/");
      return false;
    }
    return true;
  }
}
