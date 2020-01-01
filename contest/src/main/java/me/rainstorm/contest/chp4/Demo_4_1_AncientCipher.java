package me.rainstorm.contest.chp4;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 *      https://uva.onlinejudge.org/external/13/1339.pdf
 */
public class Demo_4_1_AncientCipher {
    @Test
    public void test() {
        basic("JWPUDJSTVP", "VICTORIOUS");
    }

    @Test
    public void test1() {
        basic("MAMA", "ROME");
    }

    @Test
    public void test2() {
        basic("HAHA", "HEHE");
    }

    @Test
    public void test3() {
        basic("AAA", "AAA");
    }

    @Test
    public void test4() {
        basic("NEERCISTHEBEST", "SECRETMESSAGES");
    }


    /**
     * 时间复杂度：O(nlog(n))
     * 空间复杂度：O(1)
     */
    public void basic(String str1, String str2) {
        final int SIZE = 26;
        int[] num1 = new int[SIZE];
        int[] num2 = new int[SIZE];

        for (int i = 0, len = str1.length(); i < len; ++i) {
            num1[str1.charAt(i) - 'A']++;
            num2[str2.charAt(i) - 'A']++;
        }

        Arrays.sort(num1);
        Arrays.sort(num2);

        for (int i = 0; i < SIZE; ++i) {
            if (num1[i] != num2[i]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        return;
    }

    public static void main(String[] args) throws IOException {
        Scanner cin = new Scanner(System.in);
        Demo_4_1_AncientCipher main = new Demo_4_1_AncientCipher();
        while (cin.hasNextLine()) {
            main.basic(cin.nextLine(), cin.nextLine());
        }
    }
}
