package com.footsell.dao;

import java.util.List;
import com.footsell.domain.CartListVO;
import com.footsell.domain.CartVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderDetailVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;
import com.footsell.domain.ShopReplyListVO;
import com.footsell.domain.ShopReplyVO;

public interface ShopDAO {
  List<GoodsViewVO> list(int paramInt1, int paramInt2) throws Exception;

  List<GoodsViewVO> list(int paramInt) throws Exception;

  GoodsViewVO goodsView(int paramInt) throws Exception;

  void registReply(ShopReplyVO paramShopReplyVO) throws Exception;

  List<ShopReplyListVO> shopReplyList(int paramInt) throws Exception;

  void deleteReply(ShopReplyVO paramShopReplyVO) throws Exception;

  String idCheck(int paramInt) throws Exception;

  void modifyReply(ShopReplyVO paramShopReplyVO) throws Exception;

  void addCart(CartVO paramCartVO) throws Exception;

  List<CartListVO> cartList(String paramString) throws Exception;

  void deleteCart(CartVO paramCartVO) throws Exception;

  void orderInfo(OrderVO paramOrderVO) throws Exception;

  void orderInfo_Details(OrderDetailVO paramOrderDetailVO) throws Exception;

  void cartAllDelete(String paramString) throws Exception;

  List<OrderVO> orderList(OrderVO paramOrderVO) throws Exception;

  List<OrderListVO> orderView(OrderVO paramOrderVO) throws Exception;

  void cancelOrder(String paramString) throws Exception;
}
