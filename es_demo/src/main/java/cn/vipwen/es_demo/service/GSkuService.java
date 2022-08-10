package cn.vipwen.es_demo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.vipwen.es_demo.constants.EsConsts;
import cn.vipwen.es_demo.mapper.GSkuMapper;
import cn.vipwen.es_demo.model.GSku;
import cn.vipwen.es_demo.model.SkuInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
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
        ObjectMapper mapper = new ObjectMapper();
        //关闭时间戳的功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        //现在需要在中间加一个
        mapper.registerModule(new JavaTimeModule());

        Page<GSku> page = new Page<>(pageNum, 10000);
        page.setSearchCount(false);
        //Page<GSku> skuPage = page(page, Wrappers.lambdaQuery(GSku.class).orderByAsc(GSku::getId));
        List<GSku> list = list();
        List<SkuInfo> skuInfos = BeanUtil.copyToList(list, SkuInfo.class);
        //IndexRequest indexRequest = new IndexRequest();
        //List<IndexRequest> indexRequests = new ArrayList<>();
        skuInfos.forEach(p->{
            IndexRequest request = new IndexRequest(EsConsts.SKU_INDEX_NAME);
            try {
                //填充id
                request.id(p.getId() + "");
                //先不修改id
                request.source(mapper.writeValueAsString(p), XContentType.JSON);
                request.opType(DocWriteRequest.OpType.CREATE);
                //indexRequests.add(request);
                bulkProcessor.add(request);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        });
        return "ok";
    }




}
