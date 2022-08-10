package cn.vipwen.es_demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wls
 * @date 2022/5/11 14:29
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "g_spu")
public class GSpu {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 货号
     */
    @TableField(value = "sn")
    private String sn;

    /**
     * SPU名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 副标题
     */
    @TableField(value = "caption")
    private String caption;

    /**
     * 品牌ID
     */
    @TableField(value = "brand_id")
    private Long brandId;

    /**
     * 一级分类
     */
    @TableField(value = "category1_id")
    private Long category1Id;

    /**
     * 二级分类
     */
    @TableField(value = "category2_id")
    private Long category2Id;

    /**
     * 三级分类
     */
    @TableField(value = "category3_id")
    private Long category3Id;

    /**
     * 模板ID
     */
    @TableField(value = "template_id")
    private Long templateId;

    /**
     * 运费模板id
     */
    @TableField(value = "freight_id")
    private Long freightId;

    /**
     * 图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 图片列表
     */
    @TableField(value = "images")
    private String images;

    /**
     * 售后服务
     */
    @TableField(value = "sale_service")
    private String saleService;

    /**
     * 介绍
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 规格列表
     */
    @TableField(value = "spec_items")
    private String specItems;

    /**
     * 参数列表
     */
    @TableField(value = "para_items")
    private String paraItems;

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
     * 是否上架
     */
    @TableField(value = "is_marketable")
    private String isMarketable;

    /**
     * 是否启用规格
     */
    @TableField(value = "is_enable_spec")
    private String isEnableSpec;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private String isDelete;

    /**
     * 审核状态
     */
    @TableField(value = "`status`")
    private String status;

    public static final String COL_ID = "id";

    public static final String COL_SN = "sn";

    public static final String COL_NAME = "name";

    public static final String COL_CAPTION = "caption";

    public static final String COL_BRAND_ID = "brand_id";

    public static final String COL_CATEGORY1_ID = "category1_id";

    public static final String COL_CATEGORY2_ID = "category2_id";

    public static final String COL_CATEGORY3_ID = "category3_id";

    public static final String COL_TEMPLATE_ID = "template_id";

    public static final String COL_FREIGHT_ID = "freight_id";

    public static final String COL_IMAGE = "image";

    public static final String COL_IMAGES = "images";

    public static final String COL_SALE_SERVICE = "sale_service";

    public static final String COL_INTRODUCTION = "introduction";

    public static final String COL_SPEC_ITEMS = "spec_items";

    public static final String COL_PARA_ITEMS = "para_items";

    public static final String COL_SALE_NUM = "sale_num";

    public static final String COL_COMMENT_NUM = "comment_num";

    public static final String COL_IS_MARKETABLE = "is_marketable";

    public static final String COL_IS_ENABLE_SPEC = "is_enable_spec";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_STATUS = "status";
}