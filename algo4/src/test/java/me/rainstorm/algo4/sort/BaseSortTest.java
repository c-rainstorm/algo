package me.rainstorm.algo4.sort;

import me.rainstorm.algo4.tools.FileIn;

import java.nio.file.Paths;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author baochen1.zhang
 * @date 2020.01.08
 */
public class BaseSortTest<Type extends Comparable<Type>> {
    //    protected final int REPEATED_TIME = Runtime.getRuntime().availableProcessors();
    protected final int REPEATED_TIME = 1;

    static {
        System.err.println("availableProcessors: " + Runtime.getRuntime().availableProcessors());
    }

    protected int TIMEOUT = 10;

    public static enum SortType {
        ASC, DESC
    }

    private Supplier<Sort<Type>> SortSupplier;

    public BaseSortTest(Supplier<Sort<Type>> sortSupplier) {
        SortSupplier = sortSupplier;
    }

    public BaseSortTest(Supplier<Sort<Type>> sortSupplier, int timeout) {
        SortSupplier = sortSupplier;
        TIMEOUT = timeout;
    }

    protected <T> T read(String file, Function<FileIn, T> function) throws Exception {
        try (FileIn fileIn = new FileIn(Paths.get(file).toFile())) {
            return function.apply(fileIn);
        }
    }

    public void sort(Type[] array) {
        sort(array, SortType.ASC);
    }

    public void sort(Type[] array, SortType sortType) {
        Sort<Type> algoImpl = SortSupplier.get();

        BiPredicate<Type, Type> comparePredicate = SortType.ASC.equals(sortType) ? algoImpl::less : algoImpl::more;

        long start = System.currentTimeMillis();

        algoImpl.sort(array, comparePredicate);

        long end = System.currentTimeMillis();

        assert algoImpl.isSorted(array, comparePredicate);

        System.out.println(end - start);
    }
}
