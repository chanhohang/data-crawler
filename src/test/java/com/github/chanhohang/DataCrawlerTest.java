package com.github.chanhohang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataCrawlerTest {

  @Test
  public void test() {

    DataCrawler crawler = new DataCrawler();

    Assertions.assertNotNull(crawler);
  }
}
