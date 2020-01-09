package me.rainstorm.algo4.sort;

import me.rainstorm.algo4.tools.FileIn;

import java.util.function.Supplier;

/**
 * @author baochen1.zhang
 * @date 2020.01.08
 */
public class BaseIntegerSortTest extends BaseSortTest<Integer> {

    public BaseIntegerSortTest(Supplier<Sort<Integer>> sortSupplier) {
        super(sortSupplier);
    }

    public BaseIntegerSortTest(Supplier<Sort<Integer>> sortSupplier, int timeout) {
        super(sortSupplier, timeout);
    }

    protected Integer[] ints1K() throws Exception {
        return read("data/1Kints.txt");
    }

    protected Integer[] ints2K() throws Exception {
        return read("data/2Kints.txt");
    }

    protected Integer[] ints4K() throws Exception {
        return read("data/4Kints.txt");
    }

    protected Integer[] ints8K() throws Exception {
        return read("data/8Kints.txt");
    }

    protected Integer[] ints16K() throws Exception {
        return read("data/16Kints.txt");
    }

    protected Integer[] ints32K() throws Exception {
        return read("data/32Kints.txt");
    }

    protected Integer[] ints1M() throws Exception {
        return read("data/1Mints.txt");
    }

    private Integer[] read(String file) throws Exception {
        return read(file, FileIn::readAllInts);
    }
}
