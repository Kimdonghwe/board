package com.example.demo.domain.comment.svc;


import com.example.demo.domain.entity.Comment;

import java.util.List;


public interface CommentSVC {

  public List<Comment> findbyIdAll(Long postId);

  public  Integer tototalbyIdCnt(Long postId);
}
