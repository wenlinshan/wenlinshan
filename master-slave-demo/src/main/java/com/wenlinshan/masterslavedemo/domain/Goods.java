package com.wenlinshan.masterslavedemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods")
public class Goods {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "quantity")
    private Integer quantity;

    @TableField(value = "price")
    private BigDecimal price;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_QUANTITY = "quantity";

    public static final String COL_PRICE = "price";
}