package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 题目描述：
 * https://uva.onlinejudge.org/external/4/455.pdf
 */
public class Pra_3_4_PeriodicStrings {

    @Test
    public void test() {
        System.out.println(basic("HoHoHo"));
    }

    @Test
    public void test1() {
        System.out.println(basic("HHH"));
    }

    @Test
    public void test2() {
        System.out.println(basic("abcabcabcabc"));
    }

    @Test
    public void test3() {
        System.out.println(basic("hello"));
    }

    /**
     * 时间复杂度：O()
     * 空间复杂度：O()
     *
     * -- fixme
     */
    private int basic(String str) {
        ArrayList<Integer> factors = getFactors(str.length());
        for (int factor : factors) {
            int i;
            for (i = factor; i < str.length(); ++i) {
                if (str.charAt(i) != str.charAt(i % factor)) {
                    break;
                }
            }
            if (i == str.length()) {
                return factor;
            }
        }
        return str.length();
    }

    private ArrayList<Integer> getFactors(int n) {
        ArrayList<Integer> result = new ArrayList<>((int) Math.log(n) + 1);
        result.add(1);
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                result.add(i);
                result.add(n / i);
            }
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(cin.readLine().trim());
        Pra_3_4_PeriodicStrings main = new Pra_3_4_PeriodicStrings();
        while (n-- != 0) {
            cin.readLine();
            System.out.println(main.basic(cin.readLine().trim()));
            if (n != 0) {
                System.out.println();
            }
        }
    }

}
