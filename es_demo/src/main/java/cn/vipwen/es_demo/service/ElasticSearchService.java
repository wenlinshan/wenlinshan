package cn.vipwen.es_demo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import cn.vipwen.es_demo.constants.EsConsts;
import cn.vipwen.es_demo.model.Person;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @date 2021-03-05
 */
@Service
@RequiredArgsConstructor
public class ElasticSearchService {

    private final RestHighLevelClient client;


    @SneakyThrows
    public long count(QueryBuilder queryBuilder, String... indices) {
        // ????????????
        CountRequest countRequest = new CountRequest(indices);
        countRequest.query(queryBuilder);

        // ????????????
        CountResponse countResponse = client.count(countRequest, RequestOptions.DEFAULT);
        long count = countResponse.getCount();
        return count;
    }

    @SneakyThrows
    public long countDistinct(QueryBuilder queryBuilder, String field, String... indices) {
        String distinctKey = "distinctKey"; // ?????????????????????key????????????????????????

        // ?????????????????? cardinality:????????????????????????
        CardinalityAggregationBuilder aggregationBuilder = AggregationBuilders
                .cardinality(distinctKey).field(field);

        // ???????????????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder).aggregation(aggregationBuilder);

        // ????????????
        SearchRequest searchRequest = new SearchRequest(indices);
        searchRequest.source(searchSourceBuilder);

        // ????????????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        ParsedCardinality result = searchResponse.getAggregations().get(distinctKey);
        return result.getValue();
    }

    /**
     * ????????????
     *
     * @param queryBuilder ????????????
     * @param field        ???????????????????????????????????? date ??????
     * @param interval     ???????????????????????????1??????1???
     * @param indices      ????????????
     * @return
     */
    @SneakyThrows
    public Map<String, Long> dateHistogram(QueryBuilder queryBuilder, String field, DateHistogramInterval interval, String... indices) {

        String dateHistogramKey = "dateHistogramKey"; // ?????????????????????key????????????????????????

        // ????????????
        AggregationBuilder aggregationBuilder = AggregationBuilders
                .dateHistogram(dateHistogramKey) //?????????????????????????????????????????????
                .field(field) // ???????????????
                .format("yyyy-MM-dd") // ????????????
                .calendarInterval(interval) // ????????????????????? 1s->1??? 1d->1??? 1w->1??? 1M->1??? 1y->1??? ...
                .minDocCount(0); // ???????????????????????????????????????

        // ???????????????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(queryBuilder)
                .aggregation(aggregationBuilder)
                .size(0);

        // ??????SearchRequest
        SearchRequest searchRequest = new SearchRequest(indices);
        searchRequest.source(searchSourceBuilder);

        searchRequest.indicesOptions(
                IndicesOptions.fromOptions(
                        true, // ???????????????????????????
                        true, // ???????????????????????????
                        true, // ?????????????????????????????????????????????
                        false // ?????????????????????????????????????????????
                ));

        // ????????????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // ????????????
        ParsedDateHistogram dateHistogram = searchResponse.getAggregations().get(dateHistogramKey);

        Iterator<? extends Histogram.Bucket> iterator = dateHistogram.getBuckets().iterator();

        Map<String, Long> map = new HashMap<>();
        while (iterator.hasNext()) {
            Histogram.Bucket bucket = iterator.next();
            map.put(bucket.getKeyAsString(), bucket.getDocCount());
        }
        return map;
    }

    @SneakyThrows
    public <T extends BaseDocument> List<T> search(QueryBuilder queryBuilder, Class<T> clazz, String... indices) {
        List<T> list = this.search(queryBuilder, null, 1, 10, clazz, indices);
        return list;
    }

    @SneakyThrows
    public <T extends BaseDocument> List<T> search(QueryBuilder queryBuilder, SortBuilder sortBuilder, Integer page, Integer size, Class<T> clazz, String... indices) {
        // ??????SearchSourceBuilder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.sort(sortBuilder);
        searchSourceBuilder.from((page - 1) * size);
        searchSourceBuilder.size(size);
        // ??????SearchRequest
        SearchRequest searchRequest = new SearchRequest(indices);
        searchRequest.source(searchSourceBuilder);
        // ????????????
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        List<T> list = CollectionUtil.newArrayList();
        for (SearchHit hit : searchHits) {
            T t = JSONUtil.toBean(hit.getSourceAsString(), clazz);
            t.setId(hit.getId()); // ?????????????????????
            t.setIndex(hit.getIndex());// ??????
            list.add(t);
        }
        return list;
    }

    @SneakyThrows
    public boolean deleteById(String id, String index) {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return true;
    }

    /**
     * ??????
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    public List<Person> searchPage(String keyword, int pageNo, int pageSize) throws IOException {

        SearchRequest searchRequest = new SearchRequest(EsConsts.INDEX_NAME);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        pageSize = pageSize == 0 ? 10 : pageSize;
        pageNo = (pageNo - 1) * pageSize;
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        //??????????????????
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //?????????????????????????????????
        QueryBuilder termQueryBuilder = QueryBuilders.matchQuery("remark", keyword);
        queryBuilder.must(termQueryBuilder);
        searchSourceBuilder.query(queryBuilder);
        // ????????????
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("remark")
                .preTags("<span style=color:green>")
                .postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //??????
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        if (search.getHits().getHits().length != 0) {
            //????????????
            for (SearchHit documentFields : search.getHits().getHits()) {
                Map<String, Object> source = documentFields.getSourceAsMap();
                //Person person = new Person();
                Person person = BeanUtil.mapToBean(source, Person.class, false, CopyOptions.create());
                HighlightField remark = documentFields.getHighlightFields().get("remark");
                StringBuilder s = new StringBuilder();
                for (Text fragment : remark.getFragments()) {
                    s.append(fragment);
                }
                person.setRemark(s.toString());
                people.add(person);
                list.add(source);
            }
            System.out.println(people);
        } else {
            HashMap<String, Object> map = new HashMap<>(4);
            map.put("code", 404);
            map.put("msg", "??????????????????");
            list.add(map);
        }
        return people;
    }


    @Data
    public static class BaseDocument {

        /**
         * ??????????????????
         */
        private String id;

        /**
         * ????????????
         */
        private String index;
    }


    @Data
    public class LoginRecord extends BaseDocument {

        private String clientIP;

        private long elapsedTime;

        private Object message;

        private String token;

        private String username;

        private String loginTime;

        private String region;

        /**
         * ???????????? 0-?????? 1-??????
         */
        private Integer status;

    }


}
