package com.kh.demo.domain.comment.dao;

import com.kh.demo.domain.entity.Comment;
import com.kh.demo.domain.entity.Product;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository //dao역할을 하는 클래스
public class CommentDAOImpl implements CommentDAO{


  private NamedParameterJdbcTemplate template;

  CommentDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }


  //상품 조회시 해당 상품에 해당하는 댓글 호출
  @Override
  public List<Comment> findbyIdAll(Long productId) {

    StringBuffer sql = new StringBuffer();

    sql.append("select comments_id,product_id, detail from comments ");
    sql.append("where product_id = :productId");

    try {
      Map<String,Long> map = Map.of("productId",productId);
      List<Comment> comments =template.query(sql.toString(), map, BeanPropertyRowMapper.newInstance(Comment.class));
      return comments;

    }catch (EmptyResultDataAccessException e){
      //조회결과가 없는경우

      List<Comment> comments = new ArrayList<>();
      return  comments;
    }


  }

  //게시글 아이디에 해당하는 총 댓글수 반환
  @Override
  public Integer tototalbyIdCnt(Long productId) {
    StringBuffer sql = new StringBuffer();

    sql.append("select count(comments_id) from comments ");
    sql.append("where product_id = :productId ");

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("productId",productId);
    Integer byIdCnt =template.queryForObject(sql.toString(),parameterSource,Integer.class);
    return byIdCnt;

  }
}
