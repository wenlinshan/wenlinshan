package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.service.ElasticSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wls
 * @version 1.0
 * @date 2022/5/10 16:48
 */
@RestController
@RequestMapping
public class TestController {

   @Resource
   ElasticSearchService elasticSearchService;

   @GetMapping("/es")
   public List<Map<String ,Object>> testSearch(String keyword, int pageNo, int pageSize) throws IOException {
      return elasticSearchService.searchPage(keyword, pageNo, pageSize);
   }
}
