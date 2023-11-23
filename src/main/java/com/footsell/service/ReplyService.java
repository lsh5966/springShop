package com.footsell.service;

import java.util.List;
import com.footsell.domain.ReplyVO;

public interface ReplyService {
  List<ReplyVO> list(int paramInt) throws Exception;

  void write(ReplyVO paramReplyVO) throws Exception;

  void modify(ReplyVO paramReplyVO) throws Exception;

  ReplyVO replySelect(ReplyVO paramReplyVO) throws Exception;

  int deleteWithPassword(ReplyVO paramReplyVO, String paramString) throws Exception;
}
