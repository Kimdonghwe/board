package com.example.demo.domain.comment.dao;

import com.example.demo.domain.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository //dao역할을 하는 클래스
public class CommentDAOImpl implements CommentDAO{


  private NamedParameterJdbcTemplate template;

  CommentDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }


  //상품 조회시 해당 상품에 해당하는 댓글 호출
  @Override
  public List<Comment> findbyIdAll(Long postId) {

    StringBuffer sql = new StringBuffer();

    sql.append("select comments_id,post_id, detail from comments ");
    sql.append("where post_id = :postId");

    try {
      Map<String,Long> map = Map.of("postId",postId);
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
  public Integer tototalbyIdCnt(Long postId) {
    StringBuffer sql = new StringBuffer();

    sql.append("select count(comments_id) from comments ");
    sql.append("where post_id = :postId ");

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("postId",postId);
    Integer byIdCnt =template.queryForObject(sql.toString(),parameterSource,Integer.class);
    return byIdCnt;

  }
}
