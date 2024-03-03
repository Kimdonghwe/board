package com.example.demo.domain.comment.dao;

import com.example.demo.domain.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CommentDAOImplTest {

  @Autowired
  CommentDAO commentDAO;

  @Test
  void findbyIdAll() {

    List<Comment>  comments = commentDAO.findbyIdAll(128L);

    log.info("comments = {} ", comments);
  }

  @Test
  void tototalbyIdCnt() {
  }
}