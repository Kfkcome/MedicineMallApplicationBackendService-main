package pers.ervinse.utils;

import java.lang.reflect.Method;

public class ObjectUtil {
    public static boolean isNull(Object o) {
        Class<?> clazz = o.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().startsWith("get")) {
                try {
                    Object invoke = declaredMethod.invoke(o);
                    if (invoke == null)
                        return true;
                } catch (Exception e) {
                    return true;
                }
            }
        }
        return false;
    }
}
