package cn.vipwen.chaindemo.handler;

import cn.vipwen.chaindemo.domain.User;

/**
 * @author wls
 */
public class AuthHandler extends Handler<User> {
    @Override
    public void doHandler(User user) {
        if (!"666".equals(user.getPassword())) {
            System.out.println("密码错误");
            return;
        }
        if (next != null) {
            next.doHandler(user);
        }

    }
}
