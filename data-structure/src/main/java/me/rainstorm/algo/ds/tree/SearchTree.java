package me.rainstorm.algo.ds.tree;

import java.util.Optional;

/**
 * @author chen
 */
public interface SearchTree<Key extends Comparable<Key>, Value> {
    /**
     * 添加 key-value 到搜索树
     *
     * @param key   key
     * @param value value
     * @return 如果对应 key 原来有值，返回旧值，否则返回 null
     */
    Value put(Key key, Value value);

    /**
     * 从树中获取Value，不存在时返回 Optional.empty()
     *
     * @param key key
     * @return Value
     */
    Optional<Value> get(Key key);

    /**
     * 从树中删除指定的 key
     *
     * @param key key
     * @return Value - 旧值
     */
    Value delete(Key key);

    /**
     * 判断树中是否含有指定的 key
     *
     * @param key key
     * @return true/false
     */
    boolean contains(Key key);

    /**
     * 树是否为空
     *
     * @return true/false
     */
    boolean isEmpty();

    /**
     * 树容量
     *
     * @return size
     */
    int size();

    /**
     * 最小的 key
     *
     * @return key
     */
    Key min();

    /**
     * 最大的 key
     *
     * @return key
     */
    Key max();

    /**
     * 小于等于指定key 的最大key
     *
     * @param key key
     * @return 小于等于指定key 的最大key
     */
    Key floor(Key key);

    /**
     * 大于等于指定key 的最小key
     *
     * @param key key
     * @return 大于等于指定key 的最小key
     */
    Key ceiling(Key key);

    /**
     * 指定 key 的排名
     *
     * @param key key
     * @return 在树中的排名
     */
    int rank(Key key);

    /**
     * 查找排名第 k 的 key
     *
     * @param k 排名
     * @return 排名第 k 的 key
     */
    Key select(int k);

    /**
     * 删除最小的键
     */
    void deleteMin();

    /**
     * 删除最小的键
     */
    void deleteMax();

    /**
     * [low, high] 之间键的数量
     *
     * @param low  low
     * @param high high
     * @return [low, high] 之间键的数量
     */
    int size(Key low, Key high);

    /**
     * [low, high] 之间的所有键，按排好的顺序
     *
     * @param low  low
     * @param high high
     * @return [low, high] 所有键
     */
    Iterable<Key> keys(Key low, Key high);

    /**
     * 所有键，按排好的顺序
     *
     * @return 所有键
     */
    Iterable<Key> keys();
}
