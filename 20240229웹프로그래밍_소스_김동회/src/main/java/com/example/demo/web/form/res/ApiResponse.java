package com.example.demo.web.form.res;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiProductResponse<T> {


  private Header header; //응답메세지 헤더
  private T body;        //응답메세지 바디
  private int totalCnt = 0;   //총댓글 수

  private ApiProductResponse(Header header, T body) {
    this.header = header;
    this.body = body;
  }

  @Getter
  @ToString
  @AllArgsConstructor
  static class Header {
    private String rtcd;  //응답코드
    private String rfmsg; //응답메세지
    private String rtdetail; //응답세부메세지

    public Header(String rtcd, String rfmsg) {
      this.rtcd = rtcd;
      this.rfmsg = rfmsg;
    }
  }

  public static <T> ApiProductResponse<T> createProductResponseDetail(String rtcd, String rtmsg, String rtdetail, T body) {
    return new ApiProductResponse<>(new Header(rtcd, rtmsg, rtdetail), body);
  }

  public void setTotalCnt(int totalCnt) {
    this.totalCnt = totalCnt;
  }

}
