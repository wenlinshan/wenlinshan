package cn.vipwen.es_demo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.vipwen.es_demo.constants.EsConsts;
import cn.vipwen.es_demo.mapper.GSkuMapper;
import cn.vipwen.es_demo.model.GSku;
import cn.vipwen.es_demo.model.SkuInfo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wls
 * @date 2022/5/11 14:29
 * @version 1.0
 */
@Slf4j
@Service
public class GSkuService extends ServiceImpl<GSkuMapper, GSku> {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Autowired
    BulkProcessor bulkProcessor;
    /**
     * 导入
     * @return
     */
    public String importSku(int pageNum) throws IOException {
        Page<GSku> page = new Page<>(pageNum, 10000);
        page.setSearchCount(false);
        Page<GSku> skuPage = page(page);
        List<SkuInfo> skuInfos = BeanUtil.copyToList(skuPage.getRecords(), SkuInfo.class);
        IndexRequest indexRequest = new IndexRequest();
        List<IndexRequest> indexRequests = new ArrayList<>();
        skuInfos.forEach(p->{
            IndexRequest request = new IndexRequest(EsConsts.SKU_INDEX_NAME);
            //填充id
            request.id(p.getId() + "");
            //先不修改id
            request.source(JSON.toJSONString(p), XContentType.JSON);
            request.opType(DocWriteRequest.OpType.CREATE);
            indexRequests.add(request);

        });
        indexRequests.forEach(bulkProcessor::add);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return "ok";
    }




}
