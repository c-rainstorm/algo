package me.rainstorm.chp2;

// 对于任意大于 1 的自然数 n, 若 n 为奇数，则将 n 变为 3n+1，否则将其变为原来的一半
// 经过若干次变换，一定会使 n 变为 1，

// 输入 n， 输出变换的次数

import org.junit.Test;

public class Demo_2_2_3nplus1 {

    @Test
    public void test(){
        System.out.println(basic(3));
    }

    @Test
    public void test1(){
//        若 basic 的参数是 int，则会发生溢出，所以必须使用 long 类型
        System.out.println(basic(987654321));
    }

    public int basic(long n){
        int result = 0;
        if(n < 2){
            return result;
        }

        while (n != 1){
            result++;
            if((n & 1) == 1){
//              是奇数
//              << 操作符的优先级较低，所以必须加上括号
                n = (n << 1) + n + 1;
            }else {
//              是偶数
                n >>= 1;
            }
        }

        return  result;
    }
}
