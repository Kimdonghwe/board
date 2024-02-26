package com.kh.demo.domain.comment.dao;

import com.kh.demo.domain.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CommentDAOImplTest {

  @Autowired
  CommentDAO commentDAO;

  @Test
  @DisplayName("댓글조회")
  void findbyIdAll() {

    List<Comment> comments = commentDAO.findbyIdAll(1L);

    comments.forEach(ele -> log.info("comment = {} ", ele));
  }

  @Test
  @DisplayName("댓글개수")
  void tototalbyIdCnt() {

    Integer ByIdCnt = commentDAO.tototalbyIdCnt(1L);

    log.info("ByIdCnt = {} ", ByIdCnt);
  }
}