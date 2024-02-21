package com.kh.demo.domain.pubdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class Stock {

  private final WebClient webClient;
  private String baseUrl = "https://apis.data.go.kr";

  private final String serviceKey = "HJanR2yzlPBEX%2BZ6TJ2smY%2Bo%2BWAyAazxDwi0sZsBF3AV%2BOpWPsBs%2Bcl7%2FKRr1A9o5e9B15ypLI3FqAZzm%2BIkKQ%3D%3D";
  private final String numOfRows = "10";

  private  final String pageNo = "1";
  private  final String resultType = "json";



  @Autowired
  public Stock(WebClient.Builder webClientBilder){

    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

    this.webClient = webClientBilder
            .uriBuilderFactory(factory)
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) //json포맷요청
            .build();
  }

  public String reqStockPrice(String keyword, String beginBasDt, String endBasDt){

    // http get 요청하면 http 응답메시지 수신
    Mono<String> response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo")                 //베이스url 이하 경로
                    .queryParam("serviceKey",serviceKey)    //serviceKey
                    .queryParam("numOfRows",numOfRows)       //numOfRows
                    .queryParam("pageNo",pageNo)              // pageNo
                    .queryParam("resultType",resultType)
                    .queryParam("likeItmsNm",keyword)
                    .queryParam("beginBasDt",beginBasDt)
                    .queryParam("endBasDt",endBasDt)  //resultType
//              .queryParam("sort","")                       //sort
                    .build())
            .retrieve()
            .bodyToMono(String.class);
    // http응답메시지 바디를 읽어 문자열로 반환
    String data = response.block();
    return data;
  }
}
