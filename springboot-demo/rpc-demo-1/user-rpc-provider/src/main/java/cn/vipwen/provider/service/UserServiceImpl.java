package cn.vipwen.provider.service;

import cn.vipwen.api.UserRpcService;
import cn.vipwen.pojo.UserDTO;
import org.springframework.stereotype.Service;

/**
 * @author wls
 */
@Service("userService")
public class UserServiceImpl implements UserRpcService {
    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                // 1 - 男；2 - 女
                .setGender(id % 2 + 1);
    }
}
