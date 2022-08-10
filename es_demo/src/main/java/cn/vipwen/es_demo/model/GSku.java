package cn.vipwen.es_demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * @author wls
 * @date 2022/5/11 14:29
 * @version 1.0
 */
/**
    * 商品表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "g_sku")
public class GSku {
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品条码
     */
    @TableField(value = "sn")
    private String sn;

    /**
     * SKU名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 价格（分）
     */
    @TableField(value = "price")
    private Long price;

    /**
     * 库存数量
     */
    @TableField(value = "num")
    private Integer num;

    /**
     * 库存预警数量
     */
    @TableField(value = "alert_num")
    private Integer alertNum;

    /**
     * 商品图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 商品图片列表
     */
    @TableField(value = "images")
    private String images;

    /**
     * 重量（克）
     */
    @TableField(value = "weight")
    private Integer weight;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * SPUID
     */
    @TableField(value = "spu_id")
    private Long spuId;

    /**
     * 类目ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 类目名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 品牌名称
     */
    @TableField(value = "brand_name")
    private String brandName;

    /**
     * 规格
     */
    @TableField(value = "spec")
    private String spec;

    /**
     * 销量
     */
    @TableField(value = "sale_num")
    private Integer saleNum;

    /**
     * 评论数
     */
    @TableField(value = "comment_num")
    private Integer commentNum;

    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    @TableField(value = "`status`")
    private String status;

    @TableField(value = "version")
    private Integer version;

    public static final String COL_ID = "id";

    public static final String COL_SN = "sn";

    public static final String COL_NAME = "name";

    public static final String COL_PRICE = "price";

    public static final String COL_NUM = "num";

    public static final String COL_ALERT_NUM = "alert_num";

    public static final String COL_IMAGE = "image";

    public static final String COL_IMAGES = "images";

    public static final String COL_WEIGHT = "weight";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_SPU_ID = "spu_id";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_CATEGORY_NAME = "category_name";

    public static final String COL_BRAND_NAME = "brand_name";

    public static final String COL_SPEC = "spec";

    public static final String COL_SALE_NUM = "sale_num";

    public static final String COL_COMMENT_NUM = "comment_num";

    public static final String COL_STATUS = "status";

    public static final String COL_VERSION = "version";
}