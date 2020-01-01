package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.util.Arrays;

/**
 * 问题描述：
 * 实现一个井段的猜数字游戏。给定答案的序列和用户猜的序列，
 * 统计有多少数字位正确，有多少数字在两个序列都出现过但位置不对
 */
public class Demo_3_4_MasterMindHints {

    private static int col;
    private static int row;
    private static int nums[][];

    @Test
    public void test() {
        col = 4;
        row = 6;
        nums = new int[][]{
                {1, 3, 5, 5},
                {1, 1, 2, 3},
                {4, 3, 3, 5},
                {6, 5, 5, 1},
                {6, 1, 3, 5},
                {1, 3, 5, 5}
        };
        basic();
        System.out.println();
    }

    @Test
    public void test1() {
        col = 10;
        row = 5;
        nums = new int[][]{
                {1, 2, 2, 2, 4, 5, 6, 6, 6, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 1},
                {1, 1, 2, 2, 3, 3, 4, 4, 5, 5},
                {1, 2, 1, 3, 1, 5, 1, 6, 1, 9},
                {1, 2, 2, 5, 5, 5, 6, 6, 6, 7}
        };
        basic();
        System.out.println();
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     */
    private void basic() {
        int[] answer = new int[10];
        int[] guess = new int[10];
        for (int i = 1; i < row; ++i) {

            // 每计算一行进行初始化参数。
            int match = 0;
            int notMatch = 0;
            Arrays.fill(answer, 0);
            Arrays.fill(guess, 0);

            for(int j = 0; j < col; ++j){
                if(nums[0][j] == nums[i][j]){
                    match++;
                }else {
                    answer[nums[0][j]]++;
                    guess[nums[i][j]]++;
                }
            }
            for(int j = 0; j < 10; ++j){
                if(answer[j] > 0 && guess[j] > 0){
                    notMatch++;
                }
            }
            System.out.println(match + ", " + notMatch);
        }
    }

}
