package com.footsell.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.footsell.domain.FootsellVO;
import com.footsell.domain.Page;
import com.footsell.domain.ReplyVO;
import com.footsell.service.Footsellservice;
import com.footsell.service.ReplyService;

@Controller
public class FootsellController {
  @Inject
  private Footsellservice service;

  @Inject
  private ReplyService replyService;

  @RequestMapping(value = {"/footsell/list"}, method = {RequestMethod.GET})
  public void getList(Model model) throws Exception {
    List<FootsellVO> list = null;
    list = this.service.list();
    model.addAttribute("list", list);
  }

  @RequestMapping(value = {"/footsell/write"}, method = {RequestMethod.GET})
  public void getWirte() throws Exception {}

  @RequestMapping(value = {"/footsell/write"}, method = {RequestMethod.POST})
  public String postWirte(FootsellVO vo) throws Exception {
    this.service.write(vo);
    return "redirect:/footsell/listPageSearch?num=1";
  }

  @RequestMapping(value = {"/footsell/modify"}, method = {RequestMethod.GET})
  public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
    FootsellVO vo = this.service.view(bno);
    model.addAttribute("view", vo);
  }

  @RequestMapping(value = {"/footsell/view"}, method = {RequestMethod.GET})
  public String getView(@RequestParam(value = "bno", required = false) int bno, Model model)
      throws Exception {
    FootsellVO vo = this.service.view(bno);
    model.addAttribute("view", vo);
    List<ReplyVO> reply = null;
    reply = this.replyService.list(bno);
    model.addAttribute("reply", reply);
    return "footsell/view";
  }

  @RequestMapping(value = {"/footsell/modify"}, method = {RequestMethod.POST})
  public String postModify(FootsellVO vo) throws Exception {
    this.service.modify(vo);
    return "redirect:/footsell/view?bno=" + vo.getBno();
  }

  @RequestMapping(value = {"/footsell/delete"}, method = {RequestMethod.GET})
  public String getDelete(@RequestParam("bno") int bno) throws Exception {
    this.service.delete(bno);
    return "redirect:/footsell/listPageSearch?num=1";
  }

  @RequestMapping(value = {"/footsell/listPageSearch"}, method = {RequestMethod.GET})
  public void getListPageSearch(Model model, @RequestParam("num") int num,
      @RequestParam(value = "searchType", required = false,
          defaultValue = "title") String searchType,
      @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
      @RequestParam(value = "bno", required = false) Integer bno) throws Exception {
    if (bno != null)
      this.service.viewCnt(bno);
    if (bno != null)
      this.service.updateReplyCount(bno.intValue());
    Page page = new Page();
    page.setNum(num);
    page.setCount(this.service.searchCount(searchType, keyword));
    page.setSearchType(searchType);
    page.setKeyword(keyword);
    List list = null;
    list =
        this.service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
    model.addAttribute("list", list);
    model.addAttribute("page", page);
    model.addAttribute("select", Integer.valueOf(num));
  }

  @RequestMapping(value = {"/footsell/reply/modify"}, method = {RequestMethod.GET})
  public void getMofidy(@RequestParam("bno") int bno, @RequestParam("rno") int rno, Model model)
      throws Exception {
    ReplyVO vo = new ReplyVO();
    vo.setBno(bno);
    vo.setRno(rno);
    ReplyVO reply = this.replyService.replySelect(vo);
    model.addAttribute("reply", reply);
  }
}
