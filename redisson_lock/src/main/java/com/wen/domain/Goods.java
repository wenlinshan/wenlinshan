package com.wen.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wenlinshan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    @TableId(value = "",type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    /**
    * 库存
    */
    private Integer quantity;
}