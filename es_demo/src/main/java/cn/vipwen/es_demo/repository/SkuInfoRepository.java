package cn.vipwen.es_demo.repository;

import cn.vipwen.es_demo.model.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface SkuInfoRepository extends ElasticsearchRepository<SkuInfo, Long> {

}
