package me.rainstorm.algo4.sort.converter;

import java.util.function.Function;

/**
 * @author baochen1.zhang
 * @date 2020.01.07
 */
public class IntegerElementConverter implements Function<String, Integer> {
    @Override
    public Integer apply(String s) {
        return Integer.valueOf(s);
    }
}
