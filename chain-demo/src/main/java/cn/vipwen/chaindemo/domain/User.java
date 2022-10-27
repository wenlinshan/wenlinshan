package cn.vipwen.chaindemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wls
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;
    private String password;
}
