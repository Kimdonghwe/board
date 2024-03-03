package com.example.demo.domain.comment.svc;


import com.example.demo.domain.comment.dao.CommentDAO;
import com.example.demo.domain.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSVCImpl implements CommentSVC{

  @Autowired
  CommentDAO commentDAO;
  @Override
  public List<Comment> findbyIdAll(Long postId) {
    return commentDAO.findbyIdAll(postId);
  }

  @Override
  public Integer tototalbyIdCnt(Long postId) {
    return commentDAO.tototalbyIdCnt(postId);
  }
}
