package com.kh.demo.domain.comment.svc;

import com.kh.demo.domain.comment.dao.CommentDAO;
import com.kh.demo.domain.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSVCImpl implements CommentSVC{

  @Autowired
  CommentDAO commentDAO;
  @Override
  public List<Comment> findbyIdAll(Long prodcutId) {
    return commentDAO.findbyIdAll(prodcutId);
  }

  @Override
  public Integer tototalbyIdCnt(Long product_id) {
    return commentDAO.tototalbyIdCnt(product_id);
  }
}
