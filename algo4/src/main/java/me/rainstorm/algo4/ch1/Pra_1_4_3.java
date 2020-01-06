package me.rainstorm.algo4.ch1;

/**
 * @author baochen1.zhang
 * @date 2020.01.05
 */
public class Pra_1_4_3 {
    public static void main(String[] args) {
        DrawFunction.draw(0, 100, 1, (d) -> (d * d));
        DrawFunction.draw(0.1, 10, 0.1, (d) -> (1 / d));
        DrawFunction.draw(0.1, 10, 0.1, (d) -> (1 + 1 / d));
        DrawFunction.draw(0.1, 10, 0.1, (d) -> (1 + 1 / d) * (1 + 2 / d));
        DrawFunction.draw(10, 1000, 10, (d) -> (Math.log10(2 * d) / Math.log10(d)));
    }
}
