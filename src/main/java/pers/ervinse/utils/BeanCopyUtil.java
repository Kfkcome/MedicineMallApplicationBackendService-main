package pers.ervinse.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {
    private BeanCopyUtil() {

    }

    public static <O, V> V copyBean(O source, Class<V> clazz) {
        V v;
        try {
            v = clazz.newInstance();
            BeanUtils.copyProperties(source, v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return v;
    }

    public static <O, V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }
}
