package pers.ervinse.utils;

import pers.ervinse.domain.User;
import pers.ervinse.exception.SystemException;

import static pers.ervinse.enums.ResponseCode.INTERNAL_SERVER_ERROR;

public class UserContextUtil {
    private static ThreadLocal<User> user = new ThreadLocal<>();

    public static User get() {
        return user.get();
    }

    public static boolean set(User user) {
        try {
            UserContextUtil.user.set(user);
        } catch (Exception e) {
            throw new SystemException(INTERNAL_SERVER_ERROR);
        }
        return true;
    }
}
