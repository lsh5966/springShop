package com.footsell.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.footsell.dao.ShopDAO;
import com.footsell.domain.CartListVO;
import com.footsell.domain.CartVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderDetailVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;
import com.footsell.domain.ShopReplyListVO;
import com.footsell.domain.ShopReplyVO;

@Service
public class ShopServicelmpl implements ShopService {
  @Inject
  private ShopDAO dao;

  public List<GoodsViewVO> list(int cateCode, int level) throws Exception {
    int cateCodeRef = 0;
    if (level == 1) {
      cateCodeRef = cateCode;
      return this.dao.list(cateCode, cateCodeRef);
    }
    return this.dao.list(cateCode);
  }

  public GoodsViewVO goodsView(int gdsNum) throws Exception {
    return this.dao.goodsView(gdsNum);
  }

  public void registReply(ShopReplyVO reply) throws Exception {
    this.dao.registReply(reply);
  }

  public List<ShopReplyListVO> shopReplyList(int gdsNum) throws Exception {
    return this.dao.shopReplyList(gdsNum);
  }

  public void deleteReply(ShopReplyVO reply) throws Exception {
    this.dao.deleteReply(reply);
  }

  public String idCheck(int repNum) throws Exception {
    return this.dao.idCheck(repNum);
  }

  public void modifyReply(ShopReplyVO reply) throws Exception {
    this.dao.modifyReply(reply);
  }

  public void addCart(CartVO cart) throws Exception {
    this.dao.addCart(cart);
  }

  public List<CartListVO> cartList(String userId) throws Exception {
    return this.dao.cartList(userId);
  }

  public void deleteCart(CartVO cart) throws Exception {
    this.dao.deleteCart(cart);
  }

  public void orderInfo(OrderVO order) throws Exception {
    this.dao.orderInfo(order);
  }

  public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception {
    this.dao.orderInfo_Details(orderDetail);
  }

  public void cartAllDelete(String userId) throws Exception {
    this.dao.cartAllDelete(userId);
  }

  public List<OrderVO> orderList(OrderVO order) throws Exception {
    return this.dao.orderList(order);
  }

  public List<OrderListVO> orderView(OrderVO order) throws Exception {
    return this.dao.orderView(order);
  }

  public void cancelOrder(String orderId) throws Exception {
    this.dao.cancelOrder(orderId);
  }
}
