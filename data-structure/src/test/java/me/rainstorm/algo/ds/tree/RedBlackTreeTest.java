package me.rainstorm.algo.ds.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author baochen1.zhang
 * @date 2020.04.08
 */
public class RedBlackTreeTest {

    private static Stream<int[]> RedBlackTreeTestCaseSource() {
        return Stream.of(
                new int[]{1, 2, 3},
                new int[]{11, 2, 14, 1, 7, 15, 5, 8, 4},
                IntStream.range(1, 100).toArray(),
                IntStream.range(1, 100).boxed().sorted(Collections.reverseOrder())
                        .mapToInt(value -> value)
                        .toArray(),
                IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100000)).limit(1000).toArray()
        );
    }

    @ParameterizedTest
    @MethodSource("RedBlackTreeTestCaseSource")
    public void insertTest(int[] elements) {
        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
        for (int element : elements) {
            redBlackTree.put(element, element);
            System.out.println(element);
            validRedBlackTree(redBlackTree);
        }
    }

    public void validRedBlackTree(RedBlackTree<Integer, Integer> tree) {
        RedBlackTree.RBTreeNode<Integer, Integer> root = tree.root;
        // 红黑树性质1，根节点是黑的
        assert !root.isRed();

        blackCountValid(root, 0);
    }

    private int blackCountValid(RedBlackTree.RBTreeNode<Integer, Integer> root, int blackCount) {
        if (root.isNil()) {
            return blackCount;
        }

        if (root.isRed()) {
            // 红黑树性质2，如果一个节点是红色的，则两个子节点都是黑的
            assert !(root.left.isRed() || root.right.isRed());
        } else {
            blackCount++;
        }

        int leftBlackCount = blackCountValid(root.left, blackCount);
        int rightBlackCount = blackCountValid(root.right, blackCount);
        assert leftBlackCount == rightBlackCount;
        return leftBlackCount;
    }
}
