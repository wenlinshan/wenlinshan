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
@TableName(value = "g_template")
public class GTemplate {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 模板名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 规格数量
     */
    @TableField(value = "spec_num")
    private Integer spec_num;

    /**
     * 参数数量
     */
    @TableField(value = "para_num")
    private Integer para_num;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_SPEC_NUM = "spec_num";

    public static final String COL_PARA_NUM = "para_num";
}