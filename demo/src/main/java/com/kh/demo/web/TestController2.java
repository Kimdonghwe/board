package com.kh.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/a")           // GET http://localhost:9080/a/product1/c
public class TestController2 {

  @GetMapping("/{v}/c")
  public String t1(@PathVariable("v") String value){
    log.info("value={}",value);
    return "test/url";
  }
  @GetMapping("/{v1}/{v2}")     // GET http://localhost:9080/a/product1/product2
  public String t2(
          @PathVariable("v1") String value1,
          @PathVariable("v2") String value2
  ){
    log.info("value1={},value2={}",value1,value2);
    return "test/url";
  }
  @GetMapping("/{v1}/{v2}/z") // GET http://localhost:9080/a/product1/product2/z?d=product1&e=product2
  public String t3(
      @PathVariable("v1") String value1,
      @PathVariable("v2") String value2,
      @RequestParam("d") String value3,
      @RequestParam("e") String value4
  ){
    log.info("value1={},value2={},value3={},value4={}",value1,value2,value3,value4);
    return "test/url";
  }
}
