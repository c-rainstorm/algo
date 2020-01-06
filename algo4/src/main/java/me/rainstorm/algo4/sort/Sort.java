package me.rainstorm.algo4.sort;

import java.util.function.BiPredicate;

public interface Sort<Type extends Comparable<Type>> {
    /**
     * 将数组进行排序
     *
     * @param arrayNeedSort - 待排序数组
     * @param biPredicate   二元谓词，用于自定义检测排序是正序还是倒序
     */
    void sort(Type[] arrayNeedSort, BiPredicate<Type, Type> biPredicate);

    /**
     * 二元谓词，判断 a < b 的结果
     *
     * @param a 第一个比较元素
     * @param b 第二个比较元素
     * @return 比较结果，true - a < b; else false;
     */
    boolean less(Type a, Type b);

    /**
     * 二元谓词，判断 a > b 的结果
     *
     * @param a 第一个比较元素
     * @param b 第二个比较元素
     * @return 比较结果，true - a > b; else false;
     */
    boolean more(Type a, Type b);

    /**
     * 传入一个数组，判断数组是否已排序
     *
     * @param arraySorted 已排序数组
     * @param biPredicate 二元谓词，用于自定义检测排序是正序还是倒序
     * @return true - if sorted; false - if not
     */
    boolean isSorted(Type[] arraySorted, BiPredicate<Type, Type> biPredicate);

    /**
     * 打印整个数组，单行
     *
     * @param array 待输出数组
     */
    void print(Type[] array);
}
