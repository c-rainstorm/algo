package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目描述：
 * https://uva.onlinejudge.org/external/15/1586.pdf
 */
public class Pra_3_2_MolarMass {
    @Test
    public void test() {
        System.out.printf("%.3f\n", basic("C"));
    }

    @Test
    public void test1() {
        System.out.printf("%.3f\n", basic("C6H5OH"));
    }

    @Test
    public void test2() {
        System.out.printf("%.3f\n", basic("NH2CH2COOH"));
    }

    @Test
    public void test3() {
        System.out.printf("%.3f\n", basic("C12H22O11"));
    }

    @Test
    public void test4() {
        System.out.printf("%.3f\n", basic("CHO"));
    }

    @Test
    public void test5() {
        System.out.printf("%.3f\n", basic("C2H2O2"));
    }

    @Test
    public void test6() {
        System.out.printf("%.3f\n", basic("C22H22O22N22"));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private double basic(String molecule) {
        int numC = 0;
        int numH = 0;
        int numO = 0;
        int numN = 0;
        for (int i = 0; i < molecule.length(); ++i) {
            switch (molecule.charAt(i)) {
                case 'C':
                    numC += getNum(i, molecule);
                    break;
                case 'H':
                    numH += getNum(i, molecule);
                    break;
                case 'O':
                    numO += getNum(i, molecule);
                    break;
                case 'N':
                    numN += getNum(i, molecule);
                    break;
                default:
                    break;
            }
        }
//        System.out.println("[" + numC + ", " + numH + ", " + numO + ", " + numN + "]");
        return numC * 12.01 + numH * 1.008 + numO * 16.00 + numN * 14.01;
    }

    private int getNum(int i, String molecule) {
        if (i == molecule.length() - 1
                || !Character.isDigit(molecule.charAt(i + 1))) {
            return 1;
        }
        int num = molecule.charAt(i + 1) - '0';
        if (i + 2 < molecule.length() && Character.isDigit(molecule.charAt(i + 2))) {
            num *= 10;
            num += molecule.charAt(i + 2) - '0';
        }
        return num;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        Pra_3_2_MolarMass molarMass = new Pra_3_2_MolarMass();
        int n = Integer.parseInt(cin.readLine().trim());
        for (int i = 0; i < n; ++i) {
            System.out.printf("%.3f\n", molarMass.basic(cin.readLine().trim()));
        }
    }

}
