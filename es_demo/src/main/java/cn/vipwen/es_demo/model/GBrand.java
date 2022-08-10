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
/**
    * 品牌表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "g_brand")
public class GBrand {
    /**
     * 品牌id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 品牌名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 品牌图片地址
     */
    @TableField(value = "image")
    private String image;

    /**
     * 品牌的首字母
     */
    @TableField(value = "letter")
    private String letter;

    /**
     * 排序
     */
    @TableField(value = "seq")
    private Integer seq;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_IMAGE = "image";

    public static final String COL_LETTER = "letter";

    public static final String COL_SEQ = "seq";
}