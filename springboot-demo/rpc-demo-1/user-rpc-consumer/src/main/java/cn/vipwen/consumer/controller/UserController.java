package cn.vipwen.consumer.controller;

import cn.vipwen.api.UserRpcService;
import cn.vipwen.pojo.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @Resource
    private UserRpcService userService;

    @Resource
    private UserRpcService userService2;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Integer id){
        return userService.get(id);
    }

    @GetMapping("/2/{id}")
    public UserDTO getUser2(@PathVariable("id") Integer id){
        return userService2.get(id);
    }
}
