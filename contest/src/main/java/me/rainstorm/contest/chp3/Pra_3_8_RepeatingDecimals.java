package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *  题目描述：
 *      https://uva.onlinejudge.org/external/2/202.pdf
 */
public class Pra_3_8_RepeatingDecimals {
    @Test
    public void test() {
        print(76, 25, basic(76, 25));
    }

    @Test
    public void test1() {
        print(5, 43, basic(5, 43));
    }

    @Test
    public void test2() {
        print(1, 397, basic(1, 397));
    }

    @Test
    public void test3() {
        print(1, 6, basic(1, 6));
    }

    @Test
    public void test4() {
        print(5, 7, basic(5, 7));
    }

    @Test
    public void test5() {
        print(1, 250, basic(1, 250));
    }

    @Test
    public void test6() {
        print(300, 31, basic(300, 31));
    }

    @Test
    public void test7() {
        print(655, 990, basic(655, 990));
    }

    /**
     *  思路描述：
     *      当遇到一个相同的除数时小数部分就开始循环了。
     *
     *      举个例子：
     *      76 25
     *      除数    76  1  10  0   0
     *      商      3   0  0   4   0
     *
     *      我们将 (1, 0)
     *            (10, 4)
     *            (0, 0)
     *      放入 map 中，当再次遇到除数 0 时，循环开始。
     *
     *
     *  时间复杂度：O(n)
     *  空间复杂度：O(n)
     */
    public String basic(int numerator, int denominator) {
        StringBuilder builder = new StringBuilder();
        builder.append(numerator / denominator).append('.');
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        int r = numerator % denominator;
        while (true) {
            if (map.containsKey(r)) {
                break;
            }
            map.put(r, r * 10 / denominator);
            r = r * 10 % denominator;
        }
        Iterator<Integer> i = map.keySet().iterator();
        while (i.hasNext()) {
            int next = i.next();
            if (next == r) {
                break;
            }
            builder.append(map.get(next));
        }
        builder.append('(').append(map.get(r));
        while (i.hasNext()) {
            builder.append(map.get(i.next()));
        }
        builder.append(')');
        return builder.toString();
    }

    private void print(int numerator, int denominator, String str) {
        int leftPara = str.indexOf('(');
        int len = str.indexOf(')') - leftPara - 1;
        if (len > 50) {
            StringBuilder builder = new StringBuilder(64);
            builder.append(str.substring(0, leftPara));
            builder.append(str.substring(leftPara, leftPara + 51));
            builder.append("...)");
            str = builder.toString();
        }
        System.out.printf("%d/%d = %s\n", numerator, denominator, str);
        System.out.printf("   %d = %s\n", len, "number of digits in repeating cycle");
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Pra_3_8_RepeatingDecimals main = new Pra_3_8_RepeatingDecimals();
        while (cin.hasNextInt()) {
            int numerator = cin.nextInt();
            int denominator = cin.nextInt();
            main.print(numerator, denominator, main.basic(numerator, denominator));
            System.out.println();
        }
    }

}
