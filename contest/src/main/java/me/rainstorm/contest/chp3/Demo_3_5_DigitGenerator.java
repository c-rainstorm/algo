package me.rainstorm.contest.chp3;

import org.junit.Test;

/**
 * 如果 x 加上 x 的各个数字之和得到 y，就说 x 是 y 的生成元。给出 n，求最小的生成元。
 * 无解输出 0。例如：n = 216,121, 2005 时的解分别为 198，0，1979.
 */
public class Demo_3_5_DigitGenerator {

    @Test
    public void test() {
        System.out.println(basic(216));
    }

    @Test
    public void test1() {
        System.out.println(basic(121));
    }

    @Test
    public void test3(){
        System.out.println(basic(2005));
    }

    /**
     *  时间复杂度： O(log(n) * log(n))
     *  空间复杂度： O(1)
     *
     */
    private int basic(int y) {
        int result = 0;
        for (int i = getBegin(y); i < y; ++i) {
            if (cal(i) == y) {
                result = i;
                break;
            }
        }
        return result;
    }

    private int cal(int generator) {
        int result = generator;
        while (generator != 0){
            result += generator % 10;
            generator /= 10;
        }
        return result;
    }

    private int getBegin(int y) {
        int internal = 1;
        while (y != 0) {
            internal += 9;
            y /= 10;
        }
        return y - internal < 1 ? 1 : y - internal;
    }
}
