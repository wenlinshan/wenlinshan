package cn.vipwen.stream_mybatis;

import cn.vipwen.stream_mybatis.mapper.GSkuMapper;
import cn.vipwen.stream_mybatis.pojo.GSku;
import lombok.SneakyThrows;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.stream.StreamSupport;

@SpringBootTest
class StreamMybatisApplicationTests {


    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private GSkuMapper skuMapper;


    @Test
    @SneakyThrows
    void sqlSessionWay() {
        Assertions.assertAll(() -> {
            try (SqlSession session = sqlSessionFactory.openSession();
                 Cursor<GSku> skuCursor = session.getMapper(GSkuMapper.class).getSkuList(new RowBounds(0, 1000))) {
                StreamSupport.stream(skuCursor.spliterator(), true).forEach(System.out::println);
            }
        });
    }

    @Test
    void transactionTemplateWay() {
        Assertions.assertAll(() -> transactionTemplate.executeWithoutResult(transactionStatus -> {
                    try (Cursor<GSku> userCursor = skuMapper.getSkuList(new RowBounds(0, 100))) {
                        StreamSupport.stream(userCursor.spliterator(), true).forEach(System.out::println);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        ));
    }

    @Test
    @Transactional
    void transactionalWay() {
        Assertions.assertAll(() -> {
            try (Cursor<GSku> userCursor = skuMapper.getSkuList(new RowBounds(0, 100))) {
                StreamSupport.stream(userCursor.spliterator(), true).forEach(System.out::println);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void handleWay() {
        Assertions.assertAll(() -> {
            skuMapper.getSkuAll(resultContext -> {
                //取一百条
                if (resultContext.getResultCount() == 100) {
                    resultContext.stop();
                }
                GSku sku = resultContext.getResultObject();
                System.out.println(sku);
            });
        });
    }

}
