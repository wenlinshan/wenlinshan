package com.example.zk_lock.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @NotNull(message = "id不为空")
    private Integer id;

    private String name;
    /**
     * 过期时间
     */
    private Long ttlTime;
}
