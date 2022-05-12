package cn.vipwen.vipwendynamicdatasource.aspect;

import cn.vipwen.vipwendynamicdatasource.annotation.DbSource;
import cn.vipwen.vipwendynamicdatasource.config.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 数据源切面
 *
 * @author wls
 */
@Order(-1)
@Aspect
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * 切换数据源
     * 注解在类对象，拦截有@DbSource类下所有的方法
     *
     * @param point -
     */
    @Around("@annotation(cn.vipwen.vipwendynamicdatasource.annotation.DbSource)")
    public Object around(ProceedingJoinPoint point) {
        logger.info("切换数据源======");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DbSource dbSource = method.getAnnotation(DbSource.class);
        if (Objects.nonNull(dbSource)) {
            // 切换数据源
            DataSourceContextHolder.set(dbSource.value());
        }
        try {
            return point.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceContextHolder.clear();
        }
    }

}
