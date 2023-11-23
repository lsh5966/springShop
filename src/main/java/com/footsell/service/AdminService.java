package com.footsell.service;

import java.util.List;
import com.footsell.domain.CategoryVO;
import com.footsell.domain.GoodsVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;

public interface AdminService {
  List<CategoryVO> category() throws Exception;

  void register(GoodsVO paramGoodsVO) throws Exception;

  List<GoodsViewVO> goodslist() throws Exception;

  GoodsViewVO goodsView(int paramInt) throws Exception;

  void goodsModify(GoodsVO paramGoodsVO) throws Exception;

  void goodsDelete(int paramInt) throws Exception;

  List<OrderVO> orderList() throws Exception;

  List<OrderListVO> orderView(OrderVO paramOrderVO) throws Exception;

  void delivery(OrderVO paramOrderVO) throws Exception;
}
