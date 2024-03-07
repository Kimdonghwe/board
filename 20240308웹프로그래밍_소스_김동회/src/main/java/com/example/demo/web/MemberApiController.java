package com.example.demo.web;

import com.example.demo.domain.member.svc.MemberSVC;
import com.example.demo.web.form.member.ReqExitsEmail;
import com.example.demo.web.form.res.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberSVC memberSVC;

  //회원 중복체크
  @PostMapping("/dupchk")
  public ApiResponse<?> dupchk(@RequestBody ReqExitsEmail reqExitsEmail){
    ApiResponse<?> res = null;
    log.info("reqExitsEmail={}",reqExitsEmail);
    boolean existEmail = memberSVC.existMemberId(reqExitsEmail.getEmail());
    if (existEmail) {
      res = ApiResponse.createApiResponse(ResCode.EXIST.getCode(), ResCode.EXIST.name(), null);
    }else{
      res = ApiResponse.createApiResponse(ResCode.NONE.getCode(), ResCode.NONE.name(), null);
    }
    return res;
  }
}
