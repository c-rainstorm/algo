package me.rainstorm.algo4.sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.function.BiPredicate;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
public abstract class AbstractSort<Type extends Comparable<Type>>
        implements Sort<Type> {

    @Override
    public void print(Type[] array) {
        for (Type type : array) {
            StdOut.print(type + " ");
        }
        StdOut.println();
    }

    protected void exchange(Type[] array, int i, int j) {
        Type tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Override
    public boolean less(Type a, Type b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public boolean more(Type a, Type b) {
        return a.compareTo(b) > 0;
    }

    @Override
    public boolean isSorted(Type[] arraySorted, BiPredicate<Type, Type> biPredicate) {
        for (int i = 0; i < arraySorted.length - 1; i++) {
            if (!biPredicate.test(arraySorted[i], arraySorted[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
