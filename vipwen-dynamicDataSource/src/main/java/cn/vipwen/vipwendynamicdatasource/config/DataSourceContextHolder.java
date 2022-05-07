package cn.vipwen.vipwendynamicdatasource.config;



import cn.vipwen.vipwendynamicdatasource.constant.DBConstantEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过ThreadLocal将数据源设置到每个线程上下文
 *
 * @author wls
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DBConstantEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    private static final AtomicInteger COUNTER = new AtomicInteger(-1);

    public static void set(DBConstantEnum dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    public static DBConstantEnum get() {
        return CONTEXT_HOLDER.get();
    }

    public static void clear(){
        CONTEXT_HOLDER.remove();
    }

    public static void master() {
        set(DBConstantEnum.MASTER);
        System.out.println("切换到master");
    }

    public static void slave() {
        //  轮询
        int index = COUNTER.getAndIncrement() % 2;
        if (COUNTER.get() > 9999) {
            COUNTER.set(-1);
        }
        if (index == 0) {
            set(DBConstantEnum.SLAVE1);
            System.out.println("切换到slave1");
        } else {
            set(DBConstantEnum.SLAVE2);
            System.out.println("切换到slave2");
        }
    }

}