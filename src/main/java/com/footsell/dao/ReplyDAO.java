package com.footsell.dao;

import java.util.List;
import com.footsell.domain.ReplyVO;

public interface ReplyDAO {
  List<ReplyVO> list(int paramInt) throws Exception;

  void write(ReplyVO paramReplyVO) throws Exception;

  void modify(ReplyVO paramReplyVO) throws Exception;

  void delete(ReplyVO paramReplyVO) throws Exception;

  ReplyVO replySelect(ReplyVO paramReplyVO) throws Exception;

  String getPassword(ReplyVO paramReplyVO) throws Exception;
}
