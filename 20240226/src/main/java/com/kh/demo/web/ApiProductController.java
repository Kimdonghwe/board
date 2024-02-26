package com.kh.demo.web;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.svc.ProductSVC;
import com.kh.demo.web.api.ApiResponse;
import com.kh.demo.web.form.product.AddForm;
import com.kh.demo.web.req.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController  // @Responsebody + @Controller
//@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ApiProductController {

  @Autowired
  private ProductSVC productSVC;


  //상품 목록

  @GetMapping   // GET, http://localhost:9080/api/products
  public ApiResponse<List<Product>> list(Model model){
    List<Product> list = productSVC.findAll();
    ApiResponse<List<Product>> res;
     if(!list.isEmpty() ) {
       res = ApiResponse.createApiResponse(ResCode.OK.getCode(),ResCode.OK.name(),list);
       res.setTotalCnt(productSVC.tototalCnt());
       return res;
     }
     else{
       String rtDetail = "저장된 상품이 없습니다.";
       res = ApiResponse.createApiResponseDetail(ResCode.FAIL.getCode(),ResCode.FAIL.name(),rtDetail,null);
       return res;
     }

  }

  //상품조회

  @GetMapping("/{pid}") // GET, http://localhost:9080/api/products/{pid}
  public ApiResponse<?> detail(@PathVariable("pid") Long productId){

    Optional<Product> optionalProduct = productSVC.findById(productId);
    Product product;
    ApiResponse<ResFindById> res = null;
    if(optionalProduct.isPresent()){
      product = optionalProduct.orElse(null);
      ResFindById resFindById = new ResFindById();
      BeanUtils.copyProperties(product,resFindById);
      String rtDetail = "상품번호 " + productId + "가 조회되었습니다.";
      res = ApiResponse.createApiResponseDetail(ResCode.OK.getCode(),ResCode.OK.name(),rtDetail,resFindById);
      return res;
    }
    else{

      String rtDetail = "조회하려는 상품이 존재 하지않습니다.";
      res = ApiResponse.createApiResponseDetail(ResCode.FAIL.getCode(),ResCode.FAIL.name(),rtDetail,null);
      return res;
    }


//


  }

  //상품등록

  @PostMapping      // Post, http://localhost:9080/api/products
    public ApiResponse<?> add(
          //@RequestBody : 요청메세지 바디의 json포맷 문자열=>자바객체로 매핑
            @RequestBody ReqSave reqSave){

    log.info("reqSave = {} ", reqSave);

    //1)유효성 검증

    //2) 상품등록 처리
//    product.setPname(reqSave.getPname());
//    product.setQuantity(reqSave.getQuantity());
//    product.setPrice(reqSave.getPrice());



    Product product = new Product();
    BeanUtils.copyProperties(reqSave,product);
    Long productId = productSVC.save(product);

    ResSave resSave = new ResSave(productId, reqSave.getPname());

    String rtDetail = "상품번호 " + productId + "가 등록되었습니다.";
//    ApiResponse<Product> res = ApiResponse.createApiResponse(ResCode.OK.getCode(),ResCode.OK.name(),product);
    ApiResponse<ResSave> res2 = ApiResponse.createApiResponseDetail(ResCode.OK.getCode(),ResCode.OK.name(),rtDetail,resSave);
    return res2 ;

//    응답메시지
//    ap {
//      header:{
//      rtcd:
//        rtmsg
//      }
//      body:
//      totalCnt:
//      recCnt:
//      reqPage:
//    }
    }

  //상품수정

  @PatchMapping("/{pid}")
  public ApiResponse<?> updatae(@PathVariable("pid") Long pid,
                                @RequestBody  ReqUpdate reqUpdate){

    log.info("pid = {}", pid);
    log.info("update_data = {}", reqUpdate);

    //1) 유효성체크

    //2) 수정
    Product product = new Product();
    BeanUtils.copyProperties(reqUpdate,product);
    int updateCnt = productSVC.updateById(pid,product);
    ApiResponse<ResUpdate> res = null;
    if(updateCnt == 1){
      ResUpdate resUpdate = new ResUpdate();
      BeanUtils.copyProperties(reqUpdate,resUpdate);
      resUpdate.setProductId(pid);
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(),ResCode.OK.name(),resUpdate);
      return res;
    }
    else{
      res = ApiResponse.createApiResponseDetail(ResCode.FAIL.getCode(),ResCode.FAIL.name(),"상품수정 실패",null);
      return res;
    }

  }

  //상품삭제
  @DeleteMapping("/{pid}")
  public ApiResponse<?> delete(@PathVariable("pid") Long pid){

    int deleteCnt = productSVC.deleteById(pid);
    // 1이오면 성공 0이면 실패
    ApiResponse<ResUpdate> res = null;
    if(deleteCnt == 1){
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(),ResCode.OK.name(),null);
      return res;
    }
    else{
      res = ApiResponse.createApiResponseDetail(ResCode.FAIL.getCode(),ResCode.FAIL.name(),"상품삭제 실패",null);
      return res;
    }
  }


}
