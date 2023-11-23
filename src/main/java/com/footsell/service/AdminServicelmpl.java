package com.footsell.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.footsell.dao.AdminDAO;
import com.footsell.domain.CategoryVO;
import com.footsell.domain.GoodsVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;

@Service
public class AdminServicelmpl implements AdminService {
  @Inject
  private AdminDAO dao;

  public List<CategoryVO> category() throws Exception {
    return this.dao.category();
  }

  public void register(GoodsVO vo) throws Exception {
    this.dao.register(vo);
  }

  public List<GoodsViewVO> goodslist() throws Exception {
    System.out.println("서비스");
    return this.dao.goodslist();
  }

  public GoodsViewVO goodsView(int gdsNum) throws Exception {
    return this.dao.goodsView(gdsNum);
  }

  public void goodsModify(GoodsVO vo) throws Exception {
    this.dao.goodsModify(vo);
  }

  public void goodsDelete(int gdsNum) throws Exception {
    this.dao.goodsDelete(gdsNum);
  }

  public List<OrderVO> orderList() throws Exception {
    return this.dao.orderList();
  }

  public List<OrderListVO> orderView(OrderVO order) throws Exception {
    return this.dao.orderView(order);
  }

  public void delivery(OrderVO order) throws Exception {
    this.dao.delivery(order);
  }
}
