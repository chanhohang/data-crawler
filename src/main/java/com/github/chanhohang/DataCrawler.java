package com.github.chanhohang;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;

public class DataCrawler {

  private Logger logger = LoggerFactory.getLogger(getClass());

  public void run() throws IOException, InterruptedException {

    JBrowserDriver driver = new JBrowserDriver(
        Settings.builder().timezone(Timezone.AMERICA_NEWYORK).build());

    // This will block for the page load and any
    // associated AJAX requests
    driver.get("http://www.aastocks.com/en/stocks/news/aafn/latest-news");

    // You can get status code unlike other Selenium drivers.
    // It blocks for AJAX requests and page loads after clicks
    // and keyboard events.
    logger.info("StatusCode:{}", driver.getStatusCode());

    // Returns the page source in its current state, including
    // any DOM updates that occurred after page load
    // logger.info(driver.getPageSource());

    Document doc =
        // Jsoup.connect("http://www.aastocks.com/en/ltp/rtquote.aspx?symbol=00700").get();
        Jsoup.parse(driver.getPageSource());
    logger.info(doc.title());
    logger.info(doc.text());
    Elements newsHeadlines = doc.select("#mp-itn b a");
    for (Element headline : newsHeadlines) {
      logger.info("{}\n\t{}", headline.attr("title"), headline.absUrl("href"));
    }
    // driver.executeAsyncScript("window.scrollTo(0, document.body.scrollHeight)");
    // driver.pageWait();
    // logger.info(driver.getPageSource());

    // Close the browser. Allows this thread to terminate.
    driver.quit();
  }

  public static void main(String[] args) throws Exception {
    DataCrawler crawler = new DataCrawler();
    crawler.run();
  }
}
