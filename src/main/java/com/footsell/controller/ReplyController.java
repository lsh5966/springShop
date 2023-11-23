package com.footsell.controller;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.footsell.domain.ReplyVO;
import com.footsell.service.ReplyService;

@Controller
public class ReplyController {
  @Inject
  private ReplyService replyService;

  @RequestMapping(value = {"/reply/write"}, method = {RequestMethod.POST})
  public String postWrite(ReplyVO vo,
      @RequestParam(name = "password", required = true) String password) throws Exception {
    vo.setPassword(password);
    this.replyService.write(vo);
    return "redirect:/footsell/view?bno=" + vo.getBno();
  }

  @RequestMapping(value = {"/reply/modify"}, method = {RequestMethod.GET})
  public void getMofidy(@RequestParam("bno") int bno, @RequestParam("rno") int rno, Model model)
      throws Exception {
    ReplyVO vo = new ReplyVO();
    vo.setBno(bno);
    vo.setRno(rno);
    ReplyVO reply = this.replyService.replySelect(vo);
    model.addAttribute("reply", reply);
  }

  @RequestMapping(value = {"/reply/modify"}, method = {RequestMethod.POST})
  public String postModify(ReplyVO vo, @RequestParam("password") String password, Model model)
      throws Exception {
    vo.setPassword(password);
    this.replyService.modify(vo);
    return "redirect:/footsell/view?bno=" + vo.getBno();
  }

  @ResponseBody
  @PostMapping("/reply/delete")
  public Map<String, Object> replyDelete(@RequestBody Map<String, Object> requestMap)
      throws Exception {
    Map<String, Object> resMap = new HashMap<String, Object>();

    // 요청 맵에서 값을 추출
    Map<String, Object> voMap = (Map<String, Object>) requestMap.get("vo");
    int bno = Integer.parseInt(voMap.get("bno").toString());
    int rno = Integer.parseInt(voMap.get("rno").toString());
    String password = (String) requestMap.get("password");

    // 비밀번호 확인 및 댓글 삭제
    try {
      ReplyVO vo = new ReplyVO();
      vo.setBno(bno);
      vo.setRno(rno);
      vo.setPassword(password);
      int resultCnt = replyService.deleteWithPassword(vo, password);
      resMap.put("result", resultCnt);
      // 성공적으로 삭제되면 추가로 필요한 동작 수행 (예: 페이지 새로고침)
    } catch (Exception e) {
      // 비밀번호가 일치하지 않는 경우 예외 처리
      resMap.put("result", 0);
      resMap.put("error", e.getMessage());
    }

    return resMap;
  }

}
