package me.rainstorm.algo4.ch1;

import edu.princeton.cs.algs4.StdDraw;

import java.util.function.Function;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
public class DrawFunction {
    private static class YScale {
        double min;
        double max;

        public YScale(double min, double max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void draw(double start, double end, double step, Function<Double, Double> function) {
        YScale yScale = new YScale(0, Math.max(function.apply(start), function.apply(end)));
        StdDraw.setXscale(start, end);
        StdDraw.setYscale(yScale.min, yScale.max);

        StdDraw.setPenRadius(0.005);
        double preX = start;
        double preY = function.apply(start);
        for (double current = start + step; current <= end; current += step) {
            double y = function.apply(current);

            adjustYScale(yScale, y);

            StdDraw.line(preX, preY, current, y);

            preX = current;
            preY = y;
        }
    }

    private static void adjustYScale(YScale yScale, double y) {
        if (yScale.min > y) {
            yScale.min = y;
            StdDraw.setYscale(y, yScale.max);
            return;
        }

        if (yScale.max < y) {
            yScale.max = y;
            StdDraw.setYscale(yScale.min, y);
        }
    }
}
