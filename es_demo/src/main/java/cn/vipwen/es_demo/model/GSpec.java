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
@TableName(value = "g_spec")
public class GSpec {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 规格选项
     */
    @TableField(value = "`options`")
    private String options;

    /**
     * 排序
     */
    @TableField(value = "seq")
    private Long seq;

    /**
     * 模板ID
     */
    @TableField(value = "template_id")
    private Long templateId;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_OPTIONS = "options";

    public static final String COL_SEQ = "seq";

    public static final String COL_TEMPLATE_ID = "template_id";
}