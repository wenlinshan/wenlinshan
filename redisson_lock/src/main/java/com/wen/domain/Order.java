package com.wen.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author wls
 * 
 */
@Data
@TableName(value = "`order`")
public class Order {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "`g_id`")
    private Long gId;

    @TableField(value = "`create_time`")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_G_ID = "g_id";

    public static final String COL_CREATE_TIME = "create_time";
}