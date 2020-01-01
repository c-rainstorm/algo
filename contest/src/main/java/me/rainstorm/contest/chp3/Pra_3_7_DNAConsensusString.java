package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述：
 *      https://uva.onlinejudge.org/external/13/1368.pdf
 */
public class Pra_3_7_DNAConsensusString {
    private static final int MAX_M = 50;
    private static final int MAX_N = 1000;
    private static char[][] dnas = new char[MAX_M][MAX_N];
    private static int m;
    private static int n;

    @Test
    public void test() {
        m = 5;
        n = 8;
        System.arraycopy("TATGATAC".toCharArray(), 0, dnas[0], 0, n);
        System.arraycopy("TAAGCTAC".toCharArray(), 0, dnas[1], 0, n);
        System.arraycopy("AAAGATCC".toCharArray(), 0, dnas[2], 0, n);
        System.arraycopy("TGAGATAC".toCharArray(), 0, dnas[3], 0, n);
        System.arraycopy("TAAGATGT".toCharArray(), 0, dnas[4], 0, n);
        System.out.println(basic());
    }

    /**
     *  思路描述：
     *      因为输出的 DNA 串不要求从输入中找，
     *      那么我们只要统计每个位置出现最多那个即可，这就是我们所需要的。
     *
     *  时间复杂度：O(n * m)
     *  空间复杂度：O(1)
     */
    public int basic() {
        int[] nums = new int[4];
        String nuclears = "ACGT";    // 必须是这个顺序，因为题目要求是字母序最小的。

        int hamming = 0;
        for (int i = 0; i < n; ++i) {
            Arrays.fill(nums, 0);
            for (int j = 0; j < m; ++j) {
                nums[getIndex0(dnas[j][i])]++;
            }
            int max = Integer.MIN_VALUE;
            int index = -1;
            for (int k = 0; k < 4; ++k) {
                hamming += nums[k];
                if (max < nums[k]) {
                    max = nums[k];
                    index = k;
                }
            }
            hamming -= max;
            System.out.print(nuclears.charAt(index));

        }
        System.out.println();
        return hamming;
    }

    private int getIndex0(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return -1;
    }

    public static void main(String[] args)  {
        Scanner cin = new Scanner(System.in);
//        Scanner cin = new Scanner(new FileInputStream("src/main/resources/input"));
        Pra_3_7_DNAConsensusString main = new Pra_3_7_DNAConsensusString();
        int num = cin.nextInt();
        cin.nextLine();
        while (num-- != 0) {
            m = cin.nextInt();
            n = cin.nextInt();
            cin.nextLine();
            for (int i = 0; i < m; ++i) {
                char[] chars = cin.nextLine().toCharArray();
                System.arraycopy(chars, 0, dnas[i], 0, n);
            }
            System.out.println(main.basic());
        }
    }
}
