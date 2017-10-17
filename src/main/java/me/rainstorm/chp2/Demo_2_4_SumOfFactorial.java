package me.rainstorm.chp2;

import org.junit.Test;

// 输入 n， 计算 S = 1! + 2! + 3! + ... + n! 的末 6 位，（不含前导 0） n<= 1E6
public class Demo_2_4_SumOfFactorial {

    @Test
    public void test() {
        System.out.println(sumOfFactorial(10));
    }

    @Test
    public void test1() {
        System.out.println(sumOfFactorial(1000000));
    }

    @Test
    public void test2() {
        System.out.println(sumOfFactorial(0));
    }

    @Test
    public void test3() {
        System.out.println(sumOfFactorial(-10));
    }

    /**
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     */
    public int sumOfFactorial(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            // http://www.nntdm.net/papers/nntdm-19/NNTDM-19-2-30_42.pdf
            // 其实这里为负数的阶乘提供了定义，但是懒得写了，所以直接返回 -1；
            return -1;
        }
        int result = 0;
        int temp = 1;
        final int MOD = 1000000;
        for(int i = 1; i <= n; ++i){
            temp = (temp * i) % MOD;
            result = (result + temp) % MOD;
        }
        return result % MOD;
    }

}
