package com.example.demo.web;

import com.example.demo.domain.entity.Member;
import com.example.demo.domain.member.svc.MemberSVC;
import com.example.demo.web.form.login.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping
public class LoginController {

  private final String SESSIONDATA = "UserData";
  @Autowired
  MemberSVC memberSVC;


  @GetMapping("/login")
  public String loginFrom() {

    return "login";
  }

  @PostMapping("/login")
  public String login(LoginForm loginForm,
                      HttpServletRequest request){

    //1) 유효성 체크

    //2) 회원 유무체크
    //2-1) 회원 아이디 존재유무 체크

    if (memberSVC.existMemberId(loginForm.getEmail())) {
      Optional<Member> optionalMember = memberSVC.findByEmailAndPasswd(loginForm.getEmail(),loginForm.getPasswd());

      if(optionalMember.isPresent()){  //회원정보가 있는경우

        HttpSession session = request.getSession(true);

        session.setAttribute(SESSIONDATA, optionalMember.get());
    }
      else{  //회원정보가 없는경우

        return "login";
      }

    }



    return "redirect:/";
  }

  //로그아웃
  @ResponseBody
  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {

    String flag = "NOK";
    //세션이 있으면 가져오고 없으면 생성하지 않는다.
    HttpSession session = request.getSession(false);

    //세션 제거
    if(session !=null ){
      session.invalidate();
      flag ="OK";
    }
    return flag;
  }
}
