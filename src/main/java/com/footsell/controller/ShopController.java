package com.footsell.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.footsell.domain.CartListVO;
import com.footsell.domain.CartVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.MemberVO;
import com.footsell.domain.OrderDetailVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;
import com.footsell.domain.ShopReplyListVO;
import com.footsell.domain.ShopReplyVO;
import com.footsell.service.ShopService;

@Service
@Controller
@RequestMapping({"/shop/*"})
public class ShopController {
  private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

  @Inject
  ShopService service;

  @Autowired
  private ShopService shopService;

  @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
  public void getList(@RequestParam("c") int cateCode, @RequestParam("l") int level, Model model)
      throws Exception {
    logger.info("get llist");
    List<GoodsViewVO> list = null;
    list = this.service.list(cateCode, level);
    model.addAttribute("list", list);
  }

  @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
  public void getView(@RequestParam("n") int gdsNum, Model model) throws Exception {
    logger.info("get view");
    GoodsViewVO view = this.service.goodsView(gdsNum);
    model.addAttribute("view", view);
  }

  @ResponseBody
  @RequestMapping(value = {"/view/registReply"}, method = {RequestMethod.POST})
  public void registReply(ShopReplyVO reply, HttpSession session) throws Exception {
    logger.info("regist reply");
    MemberVO member = (MemberVO) session.getAttribute("member");
    reply.setUserId(member.getUserId());
    this.service.registReply(reply);
  }

  @ResponseBody
  @RequestMapping(value = {"/view/replyList"}, method = {RequestMethod.GET})
  public List<ShopReplyListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
    logger.info("get reply list");
    List<ShopReplyListVO> reply = this.service.shopReplyList(gdsNum);
    return reply;
  }

  @ResponseBody
  @RequestMapping(value = {"/view/deleteReply"}, method = {RequestMethod.POST})
  public int getReplyList(ShopReplyVO reply, HttpSession session) throws Exception {
    logger.info("post delete reply");
    int result = 0;
    MemberVO member = (MemberVO) session.getAttribute("member");
    String userId = this.service.idCheck(reply.getRepNum());
    if (member.getUserId().equals(userId)) {
      reply.setUserId(member.getUserId());
      this.service.deleteReply(reply);
      result = 1;
    }
    return result;
  }

  @ResponseBody
  @RequestMapping(value = {"/view/modifyReply"}, method = {RequestMethod.POST})
  public int modifyReply(ShopReplyVO reply, HttpSession session) throws Exception {
    logger.info("modify reply");
    int result = 0;
    MemberVO member = (MemberVO) session.getAttribute("member");
    String userId = this.service.idCheck(reply.getRepNum());
    if (member.getUserId().equals(userId)) {
      reply.setUserId(member.getUserId());
      this.service.modifyReply(reply);
      result = 1;
    }
    return result;
  }

  @ResponseBody
  @RequestMapping(value = {"/view/addCart"}, method = {RequestMethod.POST})
  public int addCart(CartVO cart, HttpSession session) throws Exception {
    int result = 0;
    MemberVO member = (MemberVO) session.getAttribute("member");
    if (member != null) {
      cart.setUserId(member.getUserId());
      this.service.addCart(cart);
      result = 1;
    }
    return result;
  }

  @RequestMapping(value = {"/cartList"}, method = {RequestMethod.GET})
  public String getCartList(HttpSession session, Model model) throws Exception {
    logger.info("get cart list");
    MemberVO member = (MemberVO) session.getAttribute("member");
    if (member == null)
      return "redirect:/member/signin";
    String userId = member.getUserId();
    List<CartListVO> cartList = this.service.cartList(userId);
    model.addAttribute("cartList", cartList);
    return "/shop/cartList";
  }

  @ResponseBody
  @RequestMapping(value = {"/deleteCart"}, method = {RequestMethod.POST})
  public int deleteCart(HttpSession session, @RequestParam("chbox[]") List<String> chArr,
      CartVO cart) throws Exception {
    logger.info("delete cart");
    MemberVO member = (MemberVO) session.getAttribute("member");
    String userId = member.getUserId();
    int result = 0;
    int cartNum = 0;
    if (member != null) {
      cart.setUserId(userId);
      for (String i : chArr) {
        cartNum = Integer.parseInt(i);
        cart.setCartNum(cartNum);
        this.service.deleteCart(cart);
      }
      result = 1;
    }
    return result;
  }

  @RequestMapping(value = {"/cartList"}, method = {RequestMethod.POST})
  public String order(HttpSession session, OrderVO order, OrderDetailVO orderDetail)
      throws Exception {
    logger.info("order");
    MemberVO member = (MemberVO) session.getAttribute("member");
    if (member == null)
      return "redirect:/login";
    String userId = member.getUserId();
    Calendar cal = Calendar.getInstance();
    int year = cal.get(1);
    String ym = String.valueOf(year) + (new DecimalFormat("00")).format((cal.get(2) + 1));
    String ymd = String.valueOf(ym) + (new DecimalFormat("00")).format(cal.get(5));
    String subNum = "";
    for (int i = 1; i <= 6; i++)
      subNum = String.valueOf(subNum) + (int) (Math.random() * 10.0D);
    String orderId = String.valueOf(ymd) + "_" + subNum;
    order.setOrderId(orderId);
    order.setUserId(userId);
    this.service.orderInfo(order);
    orderDetail.setOrderId(orderId);
    this.service.orderInfo_Details(orderDetail);
    this.service.cartAllDelete(userId);
    return "redirect:/shop/orderList";
  }

  @RequestMapping(value = {"/orderList"}, method = {RequestMethod.GET})
  public void getOrderList(HttpSession session, OrderVO order, Model model) throws Exception {
    logger.info("get order list");
    MemberVO member = (MemberVO) session.getAttribute("member");
    String userId = member.getUserId();
    order.setUserId(userId);
    List<OrderVO> orderList = this.service.orderList(order);
    model.addAttribute("orderList", orderList);
  }

  @RequestMapping(value = {"/orderView"}, method = {RequestMethod.GET})
  public void getOrderList(HttpSession session, @RequestParam("n") String orderId, OrderVO order,
      Model model) throws Exception {
    logger.info("get order view");
    MemberVO member = (MemberVO) session.getAttribute("member");
    String userId = member.getUserId();
    order.setUserId(userId);
    order.setOrderId(orderId);
    List<OrderListVO> orderView = this.service.orderView(order);
    model.addAttribute("orderView", orderView);
  }

  @ResponseBody
  @RequestMapping({"/cancelOrder"})
  public void cancelOrder(@RequestParam("orderId") String orderId, HttpServletResponse response) {
    try {
      this.shopService.cancelOrder(orderId);
      response.sendRedirect("/shop/orderList");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
