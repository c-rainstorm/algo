package me.rainstorm.algo.ds.tree;

import java.util.Optional;

/**
 * @author baochen1.zhang
 * @date 2020.04.07
 */
public abstract class AbstractSearchTree<Key extends Comparable<Key>, Value>
        implements SearchTree<Key, Value> {

    protected int size = 0;

    @Override
    public Optional<Value> get(Key key) {
        return Optional.empty();
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(Key low, Key high) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }
}
