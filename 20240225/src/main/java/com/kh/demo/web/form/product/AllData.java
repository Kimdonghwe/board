package com.kh.demo.web.form.product;

import com.kh.demo.domain.entity.Comment;
import com.kh.demo.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AllData {
  Product product;
  List<Comment> comments;
}
