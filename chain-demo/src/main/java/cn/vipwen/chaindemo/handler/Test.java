package cn.vipwen.chaindemo.handler;

import cn.vipwen.chaindemo.domain.User;

/**
 * @author wls
 */
public class Test {
    public static void main(String[] args) {
        Handler.Builder<User> builder = new Handler.Builder<>();
        builder.addHandler(new ValidateHandler())
                .addHandler(new AuthHandler());
        Handler<User> handler = builder.build();
        User user = new User();
        user.setName("r");
        user.setPassword("666");
        handler.doHandler(user);
    }


}
