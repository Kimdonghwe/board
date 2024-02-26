package com.kh.demo.domain.comment.dao;

import com.kh.demo.domain.entity.Comment;

import java.util.List;

public interface CommentDAO {


  // 상품조회 시 해당 게시글 댓글 호출
  public List<Comment> findbyIdAll(Long prodcutId);

  // 해당 게시글 댓글 총 갯수
  public Integer tototalbyIdCnt(Long productId);
}
