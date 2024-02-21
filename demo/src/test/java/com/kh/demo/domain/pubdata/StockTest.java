package com.kh.demo.domain.pubdata;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class StockTest {

  @Autowired
  Stock stock;

  @Test
  @DisplayName("주식목록")
  void reqStockPrice() {
    String data = stock.reqStockPrice("SK하이닉스");
    log.info("stock_data = {} ", data);
  }
}