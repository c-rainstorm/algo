package me.rainstorm.chp3;

import org.junit.Test;

import java.util.Scanner;

/**
 * 题目描述：
 * https://uva.onlinejudge.org/external/103/10340.pdf
 */
public class Pra_3_9_AllInAll {
    @Test
    public void test() {
        System.out.println(basic("sequence", "subsequence"));
    }

    @Test
    public void test1() {
        System.out.println(basic("person", "compression"));
    }

    @Test
    public void test2() {
        System.out.println(basic("VERDI", "vivaVittorioEmanueleReDiItalia"));
    }

    @Test
    public void test3() {
        System.out.println(basic("caseDoesMatter", "CaseDoesMatter"));
    }

    /**
     * 思路描述：
     * <p>
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public String basic(String s, String t) {
        boolean isSub = true;
        for (int i = 0, j = 0, lenS = s.length(), lenT = t.length(); i < lenS && isSub; ) {
            for (; j < lenT; ++j) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                    j++;
                    break;
                }
            }
            if (j == lenT && i < lenS) {
                isSub = false;
            }
        }
        return isSub ? "Yes" : "No";
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Pra_3_9_AllInAll main = new Pra_3_9_AllInAll();
        while (cin.hasNext()) {
            String s = cin.next();
            String t = cin.next();
            System.out.println(main.basic(s, t));
        }
    }
}
