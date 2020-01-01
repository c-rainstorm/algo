package me.rainstorm.contest.chp3;

import org.junit.Test;

/**
 * 题目描述：
 *     把手放在键盘上时，稍不注意就会往右错一位。
 *     这样，输入 Q 会变成输入 W，输入 J 会变成输入 K 等。
 *
 *     输入一个错位后敲出的字符串（所有字母均为大写），
 *     输出打字员本来想打出的句子。输入保证合法，
 *     即一定是错位之后的字符串。
 *     例如输入中不会出现大写字母 A。
 */
public class Demo_3_2_Wertyu {

    private static final char[] CONST_TABLE = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./".toCharArray();

    @Test
    public void test(){
        basic("O S, GOMR YPFSU/".toCharArray());
    }

    /**
     *  时间复杂度：O(n)
     *  空间复杂度：O(1)
     *
     */
    public void basic(char[] str) {
        for(int i = 0, j; i < str.length; ++i){
            for(j = 0; j < CONST_TABLE.length; ++j){
                if(CONST_TABLE[j] == str[i]){
                    System.out.print(CONST_TABLE[j-1]);
                    break;
                }
            }
            if(j == CONST_TABLE.length){
                System.out.print(str[i]);
            }
        }
        System.out.println();
    }

}
