package me.rainstorm.chp2;

import org.junit.Test;

// 输出所有的型如 aabb 的 4 位完全平方数
public class Demo_2_1_aabb {

    @Test
    public void test() {
        basic();
    }

    public void basic() {
        for (int i = 1; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                int n = i * 1100 + j * 11;
                int m = (int) Math.sqrt(n);
//                todo 一个更好的解法，为什么？《算法入门经典》P21
//                int m = Math.floor(Math.sqrt(n)+0.5);
                if (n == m * m) {
                    System.out.println(n + "(" + m + ")");
                }
            }
        }
    }

    public static void main(String[] args) {
        int m = 1;
        float n = m;
        System.out.println("int: " + Integer.toBinaryString(m));
        System.out.println("float: " + Integer.toBinaryString(Float.floatToIntBits(n)));
    }
}
