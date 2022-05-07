package com.wenlinshan.masterslavedemo.aop;

import com.wenlinshan.masterslavedemo.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 设置切面 执行具体方法选择的数据源
 * @author wls
 */
@Aspect
@Component
public class DataSourceAop {

    /**
     * 需要读的方法,切面
     */
    @Pointcut("!@annotation(com.wenlinshan.masterslavedemo.annotation.Master)" +
            "&& (execution(* com.wenlinshan.masterslavedemo.service..*.select*(..)) " +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写切面
     */
    @Pointcut("@annotation(com.wenlinshan.masterslavedemo.annotation.Master) " +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.insert*(..))" +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.save*(..))" +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.add*(..))" +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.update*(..))" +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.edit*(..))" +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.delete*(..))" +
            "|| execution(* com.wenlinshan.masterslavedemo.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DataSourceContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DataSourceContextHolder.master();
    }

    @After("readPointcut()")
    public void readAfter() {
        DataSourceContextHolder.clear();
    }

    @After("writePointcut()")
    public void writeAfter() {
        DataSourceContextHolder.clear();
    }
}
