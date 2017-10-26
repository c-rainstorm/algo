package me.rainstorm.chp3;

import org.junit.Test;

/**
 * 长度为 n 的环状串有 n 种表示法，分别为从某个位置开始顺时针得到的。
 * 在这些表示法中，字典序最小的称为最小表示。
 * <p>
 * 输入一个长度为 n(n <= 100) 的环形 DNA 串(只包含 A、C、G、T 这 4 种字符)
 * 的一种表示法，你的任务是输出该环形串的最小表示。
 */
public class Demo_3_6_CircularSequence {
    @Test
    public void test() {
        System.out.println(basic("CTCC"));
    }

    @Test
    public void test1() {
        System.out.println(basic("CGAGTCAGCT"));
    }

    /**
     *  时间复杂度：O(n^2)
     *  空间复杂度：O(n)
     */
    private String basic(String str) {
        StringBuilder builder;
        int result = 0;
        for (int i = 1; i < str.length(); ++i) {
            if (isLess(i, result, str)) {
                result = i;
            }
        }
        builder = new StringBuilder(str.substring(result));
        builder.append(str.substring(0, result));
        return builder.toString();
    }

    private boolean isLess(int i, int result, String str) {
        for (int k = 0, len = str.length(); k < len;
             ++k, i = (i + 1) % len, result = (result + 1) % len) {
            if (str.charAt(i) == str.charAt(result)) {
                continue;
            } else if (str.charAt(i) < str.charAt(result)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
