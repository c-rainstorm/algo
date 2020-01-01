package me.rainstorm.contest.chp3;


import org.junit.Test;

import java.util.Scanner;

/**
 *  题目描述：
 *      https://uva.onlinejudge.org/external/15/1585.pdf
 */
public class Pra_3_1_Score {
    @Test
    public void test() {
        System.out.println(basic("OOXXOXXOOO"));
    }

    @Test
    public void test1() {
        System.out.println(basic("OOXXOOXXOO"));
    }

    @Test
    public void test2() {
        System.out.println(basic("OXOXOXOXOXOXOX"));
    }

    @Test
    public void test3() {
        System.out.println(basic("OOOOOOOOOO"));
    }

    @Test
    public void test4() {
        System.out.println(basic("OOOOXOOOOXOOOOX"));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private int basic(String str) {
        int cur = 0;
        int result = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == 'X') {
                cur = 0;
            } else {
                result += ++cur;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Pra_3_1_Score score = new Pra_3_1_Score();
        int n = cin.nextInt();
        for (int i = 0; i < n; ++i) {
            String line = cin.next();
            System.out.println(score.basic(line));
        }
    }
}
