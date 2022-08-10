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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "g_pref")
public class GPref {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分类ID
     */
    @TableField(value = "cate_id")
    private Long cateId;

    /**
     * 消费金额
     */
    @TableField(value = "buy_money")
    private Long buyMoney;

    /**
     * 优惠金额
     */
    @TableField(value = "pre_money")
    private Long preMoney;

    /**
     * 活动开始日期
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 活动截至日期
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;


    /**
     * 类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 状态
     */
    @TableField(value = "`state`")
    private String state;

    public static final String COL_ID = "id";

    public static final String COL_CATE_ID = "cate_id";

    public static final String COL_BUY_MONEY = "buy_money";

    public static final String COL_PRE_MONEY = "pre_money";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_TYPE = "type";

    public static final String COL_STATE = "state";
}