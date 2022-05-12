package cn.vipwen.es_demo.model;

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
@TableName(value = "g_category_brand")
public class GCategoryBrand {
    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌ID
     */
    private Long brandId;

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_BRAND_ID = "brand_id";
}