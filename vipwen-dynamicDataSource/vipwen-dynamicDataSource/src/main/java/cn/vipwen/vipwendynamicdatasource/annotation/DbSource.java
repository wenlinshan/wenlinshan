package cn.vipwen.vipwendynamicdatasource.annotation;

import cn.vipwen.vipwendynamicdatasource.constant.DBConstantEnum;

import java.lang.annotation.*;

/**
 * 指定数据源注解
 * @author wls
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbSource {

    /**
     * 指定 Mapper 使用的数据源, 支持类或者特定的方法
     *
     * @return -
     */
    DBConstantEnum value() default DBConstantEnum.MASTER;

}