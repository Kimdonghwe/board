package com.example.demo.domain.comment.dao;


import com.example.demo.domain.entity.Comment;

import java.util.List;

public interface CommentDAO {


  // 상품조회 시 해당 게시글 댓글 호출
  public List<Comment> findbyIdAll(Long postId);

  public List<Comment> findbyIdAll(Long postId, Long reqPage, Long reqCnt);

  // 해당 게시글 댓글 총 갯수
  public Integer tototalbyIdCnt(Long postId);

  // 게시글 작성
  public Long addComment(Long postId, String detail);

  // 댓글 삭제

  public Boolean delComment(Long commentsId);

}
