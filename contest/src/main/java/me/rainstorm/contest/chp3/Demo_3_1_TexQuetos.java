package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  题目描述：
 *      在 Tex 中，左双引号是"``",右双引号是"""。
 *      输入一篇包含双引号的文章，你的任务是把它转换成 Tex 的格式。
 */
public class Demo_3_1_TexQuetos {

    @Test
    public void test1(){
        basic(new String("\"To be or not to be,\" quoth the Bard, \"that is the question\"."));
    }

    @Test
    public void test2(){
        update();
    }

    public void basic(String str){
        boolean isLeft = true;
        for(int i = 0; i < str.length(); ++i){
            if(str.charAt(i) == '"'){
                if(isLeft){
                    System.out.print("``");
                }else {
                    System.out.print("''");
                }
                isLeft = !isLeft;
                continue;
            }
            System.out.print(str.charAt(i));
        }
        System.out.println();
    }

    public void update(){
        try(BufferedReader cin = new BufferedReader(new InputStreamReader(System.in))) {
            int ch;
            boolean isLeft = true;
            while (cin.ready()){
                if((ch = cin.read()) != -1){
                    if(ch == '"'){
                        System.out.printf("%s", isLeft ? "``" : "''");
                        isLeft = !isLeft;
                    }else {
                        System.out.printf("%c", ch);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
