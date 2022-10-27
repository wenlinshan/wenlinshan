package cn.vipwen.consumer.controller;

import cn.vipwen.api.UserRpcService;
import cn.vipwen.pojo.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wls
 */
@RestController
public class UserController {
    /**
     * 基于版本号去找到对应的实现
     * 基于注解
     *
     * @see @Reference(version = "")
     */
    @Reference(version = "${dubbo.consumer.UserService.version}")
    private UserRpcService userService;



    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Integer id){
        return userService.get(id);
    }

}
