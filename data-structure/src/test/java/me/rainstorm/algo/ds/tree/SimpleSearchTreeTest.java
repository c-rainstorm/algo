package me.rainstorm.algo.ds.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author baochen1.zhang
 * @date 2020.04.08
 */
public class SimpleSearchTreeTest {
    public static Stream<int[]> SearchTreeTestCaseSource() {
        return Stream.of(
                new int[]{43, 68, 47, 98, 89, 40, 72, 14, 3, 48},
                new int[]{1, 2, 3},
                new int[]{11, 2, 14, 1, 7, 15, 5, 8, 4},
                IntStream.range(1, 100).toArray(),
                IntStream.range(1, 100).boxed().sorted(Collections.reverseOrder())
                        .mapToInt(value -> value)
                        .toArray(),
                IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10000)).distinct().limit(1000).toArray()
        );
    }

    @ParameterizedTest
    @MethodSource("SearchTreeTestCaseSource")
    public void insertTest(int[] elements) {
        SimpleSearchTree<Integer, Integer> searchTree = new SimpleSearchTree<>();
        for (int element : elements) {
            searchTree.put(element, element);
            System.out.println(element);
            validSearchTree(searchTree);
        }
    }

    @ParameterizedTest
    @MethodSource("SearchTreeTestCaseSource")
    public void deleteTest(int[] elements) {
        System.out.println(Arrays.toString(elements));
        SimpleSearchTree<Integer, Integer> searchTree = new SimpleSearchTree<>();
        for (int element : elements) {
            searchTree.put(element, element);
            validSearchTree(searchTree);
        }

        for (int element : elements) {
            Integer val = searchTree.delete(element);
            Assertions.assertEquals(element, val);
            validSearchTree(searchTree);
        }
    }

    private void validSearchTree(SimpleSearchTree<Integer, Integer> searchTree) {
        if (searchTree.root.isNil()) {
            return;
        }

        doValid(searchTree.root);
    }

    private void doValid(SimpleSearchTree.TreeNode<Integer, Integer> root) {
        if (root.isNil()) {
            return;
        }

        assert root.left.isNil() || root.left.value < root.value;
        assert root.right.isNil() || root.right.value > root.value;

        doValid(root.left);
        doValid(root.right);
    }
}
