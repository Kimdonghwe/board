package com.kh.demo.domain.comment.svc;

import com.kh.demo.domain.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentSVC {

  public List<Comment> findbyIdAll(Long prodcutId);

  public  Integer tototalbyIdCnt(Long product_id);
}
