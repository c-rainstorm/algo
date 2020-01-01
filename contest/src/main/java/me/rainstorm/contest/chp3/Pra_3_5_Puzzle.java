package me.rainstorm.contest.chp3;

import org.junit.Test;

import java.util.Scanner;

/**
 * 题目描述：
 *      https://uva.onlinejudge.org/external/2/227.pdf
 */
public class Pra_3_5_Puzzle {
    private static final int SIZE = 5;
    private static char[][] square = new char[SIZE][SIZE];

    @Test
    public void test() {
        square[0] = "TRGSJ".toCharArray();
        square[1] = "XDOKI".toCharArray();
        square[2] = "M VLN".toCharArray();
        square[3] = "WPABE".toCharArray();
        square[4] = "UQHCF".toCharArray();
        printSquare(0, basic("ARRBBL0"));
    }

    @Test
    public void test1() {
        square[0] = "ABCDE".toCharArray();
        square[1] = "FGHIJ".toCharArray();
        square[2] = "KLMNO".toCharArray();
        square[3] = "PQRS ".toCharArray();
        square[4] = "TUVWX".toCharArray();
        printSquare(1, basic("AAALLLL0"));
    }

    @Test
    public void test2() {
        square[0] = "ABCDE".toCharArray();
        square[1] = "FGHIJ".toCharArray();
        square[2] = "KLMNO".toCharArray();
        square[3] = "PQRS ".toCharArray();
        square[4] = "TUVWX".toCharArray();
        printSquare(2, basic("AAAAABBRRRLL0"));

    }

    private void printSquare(int no, boolean pass) {
        System.out.printf("Puzzle #%d:\n", no);
        if (!pass) {
            System.out.println("This puzzle has no final configuration.");
            return;
        }
        for (int i = 0; i < SIZE; ++i) {
            System.out.printf("%c %c %c %c %c\n",
                    square[i][0], square[i][1], square[i][2], square[i][3], square[i][4]);
        }
    }
    /**
     *  时间复杂度：O(n)
     *  空间复杂度：O(1)
     */
    private boolean basic(String seq) {
        Point space = new Point();
        boolean pass = true;
        for (int i = 0, len = seq.length() - 1; i < len && pass; ++i) {
            switch (seq.charAt(i)) {
                case 'A':
                    pass = space.moveAbove();
                    break;
                case 'B':
                    pass = space.moveBelow();
                    break;
                case 'R':
                    pass = space.moveRight();
                    break;
                case 'L':
                    pass = space.moveLeft();
                    break;
                default:
                    break;
            }
        }
        return pass;
    }

    class Point {
        private int x;
        private int y;

        public Point() {
            for (int i = 0; i < SIZE; ++i) {
                for (int j = 0; j < SIZE; ++j) {
                    if (square[i][j] == ' ') {
                        x = i;
                        y = j;
                    }
                }
            }
        }

        public boolean moveLeft() {
            if (y == 0) {
                return false;
            }
            square[x][y] = square[x][y - 1];
            square[x][y - 1] = ' ';
            y -= 1;
            return true;
        }

        public boolean moveAbove() {
            if (x == 0) {
                return false;
            }
            square[x][y] = square[x - 1][y];
            square[x - 1][y] = ' ';
            x -= 1;
            return true;
        }

        public boolean moveBelow() {
            if (x == SIZE - 1) {
                return false;
            }
            square[x][y] = square[x + 1][y];
            square[x + 1][y] = ' ';
            x += 1;
            return true;
        }

        public boolean moveRight() {
            if (y == SIZE - 1) {
                return false;
            }
            square[x][y] = square[x][y + 1];
            square[x][y + 1] = ' ';
            y += 1;
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int num = 0;
        while (true){
            if(!processSquare(cin, ++num)){
                break;
            }
        }
    }

    private static boolean processSquare(Scanner cin, int no) {
        String firstLine = cin.nextLine();
        if(firstLine.length() == 1 && firstLine.charAt(0) == 'Z'){
            return false;
        }

        for(int i = 0; i < SIZE; ++i){
            char[] chars;
            if(i == 0){
                chars = firstLine.toCharArray();
            }else{
                chars = cin.nextLine().toCharArray();
            }
            System.arraycopy(chars, 0, square[i], 0, chars.length);
            if(chars.length != SIZE){
                square[i][SIZE-1] = ' ';
            }
        }

        StringBuilder builder = new StringBuilder(10);
        while (true){
            builder.append(cin.nextLine());
            if(builder.charAt(builder.length() - 1) == '0'){
                break;
            }
        }

        Pra_3_5_Puzzle main = new Pra_3_5_Puzzle();
        if(no != 1){
            System.out.println();
        }
        main.printSquare(no, main.basic(builder.toString()));

        return true;
    }
}

