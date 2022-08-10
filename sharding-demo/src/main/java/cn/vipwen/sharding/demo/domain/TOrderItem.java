package cn.vipwen.sharding.demo.domain;

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
public class TOrderItem {
    private Long itemId;

    private String orderNo;

    private Long orderId;

    private String itemName;

    private BigDecimal price;

    public static final String COL_ITEM_ID = "item_id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_ITEM_NAME = "item_name";

    public static final String COL_PRICE = "price";
}