package cn.vipwen.stream_mybatis.mapper;

import cn.vipwen.stream_mybatis.pojo.GSku;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
* @author Administrator
* @description 针对表【g_sku(商品表)】的数据库操作Mapper
* @createDate 2023-02-17 15:43:34
* @Entity cn.vipwen.stream_mybatis.pojo.GSku
*/
public interface GSkuMapper {

    /**
     * 流式分页查询全部sku
     * @param rowBounds 分页条件
     * @return sku列表
     */
    Cursor<GSku> getSkuList(RowBounds rowBounds);

    /**
     * 流式查询所有的结果
     * @param handler 结果映射
     */
    void getSkuAll(ResultHandler<GSku> handler);

}




