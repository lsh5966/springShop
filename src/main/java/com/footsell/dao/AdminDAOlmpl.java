package com.footsell.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.footsell.domain.CategoryVO;
import com.footsell.domain.GoodsVO;
import com.footsell.domain.GoodsViewVO;
import com.footsell.domain.OrderListVO;
import com.footsell.domain.OrderVO;

@Repository
public class AdminDAOlmpl implements AdminDAO {
  @Inject
  private SqlSession sql;

  private static String namespace = "com.footsell.mappers.adminMapper";

  public List<CategoryVO> category() throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".category");
  }

  public void register(GoodsVO vo) throws Exception {
    this.sql.insert(String.valueOf(namespace) + ".register", vo);
  }

  public List<GoodsViewVO> goodslist() throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".goodslist");
  }

  public GoodsViewVO goodsView(int gdsNum) throws Exception {
    return (GoodsViewVO) this.sql.selectOne(String.valueOf(namespace) + ".goodsView",
        Integer.valueOf(gdsNum));
  }

  public void goodsModify(GoodsVO vo) throws Exception {
    this.sql.update(String.valueOf(namespace) + ".goodsModify", vo);
  }

  public void goodsDelete(int gdsNum) throws Exception {
    this.sql.delete(String.valueOf(namespace) + ".goodsDelete", Integer.valueOf(gdsNum));
  }

  public List<OrderVO> orderList() throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".orderList");
  }

  public List<OrderListVO> orderView(OrderVO order) throws Exception {
    return this.sql.selectList(String.valueOf(namespace) + ".orderView", order);
  }

  public void delivery(OrderVO order) throws Exception {
    this.sql.update(String.valueOf(namespace) + ".delivery", order);
  }
}
