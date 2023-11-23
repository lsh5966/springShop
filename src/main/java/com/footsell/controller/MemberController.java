package com.footsell.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.footsell.domain.MemberVO;
import com.footsell.service.MemberService;

@Controller
@RequestMapping({"/member/*"})
public class MemberController {
  private static final Logger Logger = LoggerFactory.getLogger(MemberController.class);

  @Inject
  MemberService service;

  @Autowired
  BCryptPasswordEncoder passEncoder;

  @RequestMapping(value = {"/signin"}, method = {RequestMethod.GET})
  public void getSignin() throws Exception {
    Logger.info("get signin");
  }

  @RequestMapping(value = {"/signin"}, method = {RequestMethod.POST})
  public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr)
      throws Exception {
    Logger.info("post signin");
    MemberVO login = this.service.signin(vo);
    HttpSession session = req.getSession();
    if (login != null) {
      boolean passMatch = this.passEncoder.matches(vo.getUserPass(), login.getUserPass());
      if (passMatch) {
        session.setAttribute("member", login);
      } else {
        session.setAttribute("member", null);
        rttr.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
        return "redirect:/member/signin";
      }
    } else {
      session.setAttribute("member", null);
      rttr.addFlashAttribute("msg", "멤버 아이디가 존재하지 않습니다.");
      return "redirect:/member/signin";
    }
    return "redirect:/";
  }

  @RequestMapping(value = {"/signup"}, method = {RequestMethod.GET})
  public void getSignup() throws Exception {
    Logger.info("get signup");
  }

  @RequestMapping(value = {"/signup"}, method = {RequestMethod.POST})
  public String postSignup(MemberVO vo) throws Exception {
    Logger.info("post signup");
    String inputPass = vo.getUserPass();
    String pass = this.passEncoder.encode(inputPass);
    vo.setUserPass(pass);
    this.service.signup(vo);
    return "redirect:/";
  }

  @RequestMapping(value = {"/signout"}, method = {RequestMethod.GET})
  public String signout(HttpSession session) throws Exception {
    Logger.info("get logout");
    this.service.signout(session);
    return "redirect:/";
  }
}
