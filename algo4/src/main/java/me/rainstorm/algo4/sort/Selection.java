package me.rainstorm.algo4.sort;

import java.util.function.BiPredicate;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
public class Selection<Type extends Comparable<Type>> extends AbstractSort<Type> {
    @Override
    public void sort(Type[] arrayNeedSort, BiPredicate<Type, Type> biPredicate) {
        for (int i = 0; i < arrayNeedSort.length; i++) {
            int min = i;
            for (int j = i + 1; j < arrayNeedSort.length; j++) {
                if (biPredicate.test(arrayNeedSort[j], arrayNeedSort[min])) {
                    min = j;
                }
            }
            exchange(arrayNeedSort, i, min);
        }
    }
}
