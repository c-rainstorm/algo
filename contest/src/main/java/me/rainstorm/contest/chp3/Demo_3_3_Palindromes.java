package me.rainstorm.contest.chp3;

import org.junit.Test;


/**
 * 问题描述
 * 输入一个字符串，判断它是否为回文串以及镜像串。输入字符串保证不含数字 0。
 * 所谓回文串，就是反转以后和原串相同，如 abba 和 madam。所有镜像串，就是左右镜像之后和原串相同，
 * 如 2S 和 3AIAE。注意，并不是每个字符在镜像之后都能得到一个合法字符。在本题中，每个字符的镜像如下：
 * <p>
 * A-Z1-9
 * "A   3  HIL JM O   2TUVWXY51SE Z  8 "
 */
public class Demo_3_3_Palindromes {

    private static final char[] MIRROR = "A   3  HIL JM O   2TUVWXY51SE Z  8 ".toCharArray();
    private static String[] msgs = new String[]{
            "is not a palindrome.",
            "is a regular palindrome.",
            "is a mirrored string.",
            "is a mirrored palindrome."
    };

    @Test
    public void test1() {
        basic("NOTAPALINDROME");
    }

    @Test
    public void test2() {
        basic("ISAPALINILAPASI");
    }

    @Test
    public void test3() {
        basic("2A3MEAS");
    }

    @Test
    public void test4() {
        basic("ATOYOTA");
    }

    /**
     * 时间复杂度： O(n)
     * 空间复杂度: O(n) // toCharArray() 方法 new 了一个新的数组并拷贝源数据到新数组
     */
    public void basic(String str) {
        char[] chars = str.toCharArray();
        int isPalindrome = 1;
        int isMirror = 1;
        for (int i = 0, len = chars.length >> 1; (isPalindrome == 1 || isMirror == 1) && i < len; ++i) {
            if (chars[i] != chars[chars.length - i - 1]) {
                isPalindrome = 0;
            }
            if (chars[i] != getMirror(chars[chars.length - i - 1])) {
                isMirror = 0;
            }
        }
        System.out.printf("%s -- %s\n", str, msgs[2 * isMirror + isPalindrome]);
    }

    private char getMirror(char origin) {
        if (Character.isDigit(origin)) {
            return MIRROR[origin - '0' + 25];
        }
        return MIRROR[origin - 'A'];
    }

}
