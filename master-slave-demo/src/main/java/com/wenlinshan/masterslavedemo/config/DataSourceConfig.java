package com.wenlinshan.masterslavedemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.wenlinshan.masterslavedemo.constant.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 关于数据源配置，参考SpringBoot官方文档第79章《Data Access》
 * 79. Data Access
 * 79.1 Configure a Custom DataSource
 * 79.2 Configure Two DataSources
 *
 * @author wls
 */

@Configuration
public class DataSourceConfig {

    /**
     * 配置主数据源
     *
     * @return 数据源
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master" )
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置从数据源
     *
     * @return 数据源
     */
    @Bean(name = "slave1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave1")
    public DataSource slave1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置从数据源
     *
     * @return 数据源
     */
    @Bean(name = "slave2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave2")
    public DataSource slave2DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置路由数据源
     *
     * @param masterDataSource 主节点
     * @param slave1DataSource 从节点
     * @param slave2DataSource 从节点
     * @return 数据源
     */
    @Bean
    public DataSource myRoutingDataSource(@Qualifier("master") DataSource masterDataSource,
                                          @Qualifier("slave1") DataSource slave1DataSource,
                                          @Qualifier("slave2") DataSource slave2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        //设置默认数据源
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }

}