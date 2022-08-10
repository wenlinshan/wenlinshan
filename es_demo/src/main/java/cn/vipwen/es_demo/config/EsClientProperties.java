package cn.vipwen.es_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author wls
 * @version 1.0
 * @date 2022/5/30 16:32
 */
@Data
@ConfigurationProperties(prefix = "spring.elasticsearch.rest")
public class EsClientProperties {

    /**
     * es连接地址
     */
    private List<String> clusterNodes;
    /**
     * es用户名
     */
    private String username;
    /**
     * es 密码
     */
    private String password;




}
