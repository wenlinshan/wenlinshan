package cn.vipwen.es_demo.service;

import cn.vipwen.es_demo.mapper.GBrandMapper;
import cn.vipwen.es_demo.model.GBrand;
import cn.vipwen.es_demo.model.GCategory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author wls
 * @version 1.0
 * @date 2022/5/11 14:29
 */
@Service
public class GBrandService extends ServiceImpl<GBrandMapper, GBrand> {
    @Autowired
    PlatformTransactionManager transactionManager;

    @Qualifier("executorThreadPool")
    @Autowired
    private Executor executorThreadPool;
    @Autowired
    private GCategoryService gCategoryService;

    @Autowired
    SqlSession sqlSession;

    @Transactional
    public boolean saveBrand(GBrand brand) throws SQLException {
        Connection connection = sqlSession.getConnection();
        try {
            connection.setAutoCommit(false);
            //AtomicBoolean rollbackFlag = new AtomicBoolean(false);
            CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
                //DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                //def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                //TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态*/
                //try {
                    brand.setId(1527180830043926531L);
                    brand.setName(brand.getName() + "1");
                    /*rollbackFlag.set(!this.save(brand));
                    if (rollbackFlag.get()) {
                        transactionManager.rollback(status);
                    } else {
                        transactionManager.commit(status);
                    }
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    rollbackFlag.set(true);
                    e.printStackTrace();
                }*/
            }, executorThreadPool);
            CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
                //DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                //def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                //TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                //try {
                    GCategory gCategory = new GCategory();
                    gCategory.setName("汽车");
                    int a = 1 / 0;
                    gCategoryService.save(gCategory);
                    /*if (rollbackFlag.get()) {
                        transactionManager.rollback(status);
                    } else {
                        transactionManager.commit(status);
                    }
                } catch (Exception e) {
                    transactionManager.rollback(status);
                    rollbackFlag.set(true);
                    e.printStackTrace();
                }*/

            }, executorThreadPool);
            CompletableFuture.allOf(future1, future2).join();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();

        }

        return true;
    }

}
