package cn.vipwen.sharding.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *
 * @author wls
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TOrder {
    @TableId(value = "order_id")
    private Long orderId;

    private String orderNo;

    private String createName;

    private BigDecimal price;

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_CREATE_NAME = "create_name";

    public static final String COL_PRICE = "price";
}