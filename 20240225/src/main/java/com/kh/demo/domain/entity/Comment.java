package com.kh.demo.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Comment {

  private Long commentsId;
  private Long productId;
  private String detail ;
}
