package com.footsell.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.footsell.domain.CartListVO;
import com.footsell.domain.CartVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderDetailVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;
import com.footsell.domain.ShopReplyListVO;
import com.footsell.domain.ShopReplyVO;

@Repository
public class ShopDAOlmpl implements ShopDAO {
  @Inject
  private SqlSession sql;

  private static String namespace = "com.footsell.mappers.shopMapper";

  public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateCode", Integer.valueOf(cateCode));
    map.put("cateCodeRef", Integer.valueOf(cateCodeRef));
    return this.sql.selectList(String.valueOf(namespace) + ".list_1", map);
  }

  public List<GoodsViewVO> list(int cateCode) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".list_2", Integer.valueOf(cateCode));
  }

  public GoodsViewVO goodsView(int gdsNum) throws Exception {
    return (GoodsViewVO) this.sql.selectOne("com.footsell.mappers.adminMapper.goodsView",
        Integer.valueOf(gdsNum));
  }

  public void registReply(ShopReplyVO reply) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".registReply", reply);
  }

  public List<ShopReplyListVO> shopReplyList(int gdsNum) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".shopReplyList",
        Integer.valueOf(gdsNum));
  }

  public void deleteReply(ShopReplyVO reply) throws Exception {
    this.sql.delete(String.valueOf(namespace) + ".deleteReply", reply);
  }

  public String idCheck(int repNum) throws Exception {
    return (String) this.sql.selectOne(String.valueOf(namespace) + ".replyUserIdCheck",
        Integer.valueOf(repNum));
  }

  public void modifyReply(ShopReplyVO reply) throws Exception {
    this.sql.update(String.valueOf(namespace) + ".modifyReply", reply);
  }

  public void addCart(CartVO cart) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".addCart", cart);
  }

  public List<CartListVO> cartList(String userId) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".cartList", userId);
  }

  public void deleteCart(CartVO cart) throws Exception {
    this.sql.delete(String.valueOf(namespace) + ".deleteCart", cart);
  }

  public void orderInfo(OrderVO order) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".orderInfo", order);
  }

  public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".orderInfo_Details", orderDetail);
  }

  public void cartAllDelete(String userId) throws Exception {
    this.sql.delete(String.valueOf(namespace) + ".cartAllDelete", userId);
  }

  public List<OrderVO> orderList(OrderVO order) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".orderList", order);
  }

  public List<OrderListVO> orderView(OrderVO order) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".orderView", order);
  }

  public void cancelOrder(String orderId) throws Exception {
    Map<String, String> parameters = new HashMap<String, String>();
    parameters.put("orderId", orderId);
    this.sql.delete(String.valueOf(namespace) + ".cancelOrderDetails", parameters);
    this.sql.delete(String.valueOf(namespace) + ".cancelOrder", parameters);
  }
}
