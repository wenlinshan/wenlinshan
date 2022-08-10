package cn.vipwen.es_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ElasticSearch HighLevelClient
 * @author wls
 */
@Slf4j
@EnableConfigurationProperties(value = {EsClientProperties.class})
@Configuration
public class RestHighLevelClientConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient(EsClientProperties esClientProperties) {
        HttpHost[] hosts = esClientProperties.getClusterNodes().stream()
                // eg: new HttpHost("127.0.0.1", 9200, "http")
                .map(this::buildHttpHost)
                .toArray(HttpHost[]::new);
        RestClientBuilder builder = RestClient.builder(hosts);
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //填充用户名密码
        if (Objects.nonNull(esClientProperties.getUsername()) && Objects.nonNull(esClientProperties.getPassword())) {
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esClientProperties.getUsername(), esClientProperties.getPassword()));
        }
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(30);
            httpClientBuilder.setMaxConnPerRoute(30);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            // 该方法接收一个RequestConfig.Builder对象，对该对象进行修改后然后返回。
            @Override
            public RequestConfig.Builder customizeRequestConfig(
                    RequestConfig.Builder requestConfigBuilder) {
                // 连接超时（默认为1秒）
                // 套接字超时（默认为30秒）//更改客户端的超时限制默认30秒现在改为100*1000分钟
                return requestConfigBuilder.setConnectTimeout(5000 * 1000)
                        .setSocketTimeout(6000 * 1000);
            }
        });//

        return new RestHighLevelClient(builder);

    }

    /**
     * 构建连接地址
     *
     * @param node 连接地址
     * @return httpHost
     */
    private HttpHost buildHttpHost(String node) {
        String[] nodeInfo = node.split(":");
        return new HttpHost(nodeInfo[0].trim(), Integer.parseInt(nodeInfo[1].trim()), "http");
    }


    /**
     * 批量处理数据
     *
     * @param restHighLevelClient 客户端
     * @return 批量处理器
     */
    @Bean
    public BulkProcessor bulkProcessor(RestHighLevelClient restHighLevelClient) {

        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                log.info("1. 【beforeBulk】批次[{}] 携带 {} 请求数量", executionId, request.numberOfActions());
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                if (response.hasFailures()) {
                    BulkItemResponse[] items = response.getItems();
                    for (BulkItemResponse item : items) {
                        if (item.isFailed()) {
                            log.info("2. 【afterBulk-失败】批量 [{}] 出现异常的原因 : {}", executionId, item.getFailureMessage());
                            break;
                        }
                    }
                } else {
                    log.info("2. 【afterBulk-成功】批量 [{}] 完成在 {} ms", executionId, response.getTook().getMillis());
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  Throwable failure) {

                List<DocWriteRequest<?>> requests = request.requests();
                List<String> esIds = requests.stream().map(DocWriteRequest::id).collect(Collectors.toList());
                log.error("3. 【afterBulk-failure失败】es执行bluk失败,失败的esId为：{}", esIds, failure);
                try {
                    throw failure;
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };

        BulkProcessor.Builder builder = BulkProcessor.builder(((bulkRequest, bulkResponseActionListener) -> {
            restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener);
        }), listener);
        //到达10000条时刷新
        builder.setBulkActions(500);
        //内存到达8M时刷新
        builder.setBulkSize(new ByteSizeValue(8L, ByteSizeUnit.MB));
        //设置的刷新间隔10s
        builder.setFlushInterval(TimeValue.timeValueSeconds(10));
        //设置允许执行的并发请求数。
        builder.setConcurrentRequests(8);
        //设置重试策略
        builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1), 3));
        return builder.build();
    }
}
