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
 * @author wls
 * @version 1.0
 * @date 2022/5/11 14:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`g_album`")
public class GAlbum {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 相册名称
     */
    @TableField(value = "`title`")
    private String title;

    /**
     * 相册封面
     */
    @TableField(value = "`image`")
    private String image;

    /**
     * 图片列表
     */
    @TableField(value = "`image_items`")
    private String imageItems;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_IMAGE = "image";

    public static final String COL_IMAGE_ITEMS = "image_items";
}