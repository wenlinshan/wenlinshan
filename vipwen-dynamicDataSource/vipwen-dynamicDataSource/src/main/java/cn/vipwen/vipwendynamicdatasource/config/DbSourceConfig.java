package cn.vipwen.vipwendynamicdatasource.config;

import cn.vipwen.vipwendynamicdatasource.aspect.DynamicDataSourceAspect;
import cn.vipwen.vipwendynamicdatasource.constant.DBConstantEnum;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wls
 */
@EnableTransactionManagement
@Configuration
public class DbSourceConfig {
    /**
     * 配置主数据源
     *
     * @return 数据源
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
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
     * @return 数据源
     */
    @ConditionalOnBean(name = {"master"})
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DBConstantEnum.MASTER, masterDataSource());
        targetDataSources.put(DBConstantEnum.SLAVE1, slave1DataSource());
        targetDataSources.put(DBConstantEnum.SLAVE2, slave2DataSource());
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     * 数据库连接会话工厂
     * 将动态数据源赋给工厂
     *
     * @param dynamicDataSource 动态数据源
     * @return 数据库连接会话工厂
     */
    @ConditionalOnBean(name = "dynamicDataSource")
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        return sqlSessionFactory.getObject();
    }

    /**
     * 事务管理器
     *
     * @param dynamicDataSource 动态数据源
     * @return 事务管理器
     */
    @ConditionalOnBean(name = "dynamicDataSource")
    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    /**
     * 动态数据源切面配置
     * @return 动态数据源切面
     */
    @Bean
    public DynamicDataSourceAspect dynamicDataSourceAspect() {
        return new DynamicDataSourceAspect();
    }
}
