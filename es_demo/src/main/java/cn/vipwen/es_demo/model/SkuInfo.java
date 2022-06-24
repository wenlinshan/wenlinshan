package cn.vipwen.es_demo.model;

import cn.vipwen.es_demo.constants.EsConsts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author wls
 * @version 1.0
 * @date 2022/5/11 15:02
 */
@Document(indexName = EsConsts.SKU_INDEX_NAME)
@Data
public class SkuInfo implements Serializable {
    /**
     * 商品id，同时也是商品编号
     */
    @Id
    @Field(index = true, store = true, type = FieldType.Keyword)
    private Long id;

    /**
     * SKU名称
     */
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String name;

    /**
     * 商品价格，单位为：元
     */
    @Field(index = true, store = true, type = FieldType.Double)
    private Long price;

    /**
     * 库存数量
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer num;

    /**
     * 商品图片
     */
    @Field(index = false, store = true, type = FieldType.Text)
    private String image;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field( type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field( type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 是否默认
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String isDefault;

    /**
     * SPUID
     */
    @Field(index = true, store = true, type = FieldType.Long)
    private Long spuId;

    /**
     * 类目ID
     */
    @Field(index = true, store = true, type = FieldType.Long)
    private Long categoryId;

    /**
     * 类目名称
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName;

    /**
     * 品牌名称
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String brandName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 规格参数
     */
    private Map<String, Object> specMap;
}
