package cn.vipwen.chaindemo.handler;

import cn.vipwen.chaindemo.domain.User;
import org.springframework.util.ObjectUtils;


/**
 * @author wls
 */
public class ValidateHandler extends Handler<User> {
    @Override
    public void doHandler(User user) {
        if (ObjectUtils.isEmpty(user.getName()) ||
                ObjectUtils.isEmpty(user.getPassword())) {
            System.out.println("用户名和密码不能为空");
            return;
        }
        if (null != next) {
            next.doHandler(user);
        }
    }
}
