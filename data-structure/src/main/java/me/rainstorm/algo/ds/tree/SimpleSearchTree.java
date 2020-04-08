package me.rainstorm.algo.ds.tree;

import lombok.Data;
import lombok.ToString;

import static me.rainstorm.algo.ds.tree.SimpleSearchTree.TreeNode.NIL;

/**
 * @author baochen1.zhang
 * @date 2020.04.08
 */
public class SimpleSearchTree<Key extends Comparable<Key>, Value> extends AbstractSearchTree<Key, Value> {
    protected TreeNode<Key, Value> root = NIL;

    @Override
    public Value put(Key key, Value value) {
        if (root == NIL) {
            root = new TreeNode<>(key, value);
            return null;
        }

        return put(root, key, value);
    }

    private Value put(SimpleSearchTree.TreeNode<Key, Value> root, Key key, Value value) {
        while (true) {
            int cmp = root.key.compareTo(key);
            boolean found = cmp == 0;
            boolean onLeftSubTree = cmp > 0;
            boolean onRightSubTree = cmp < 0;

            if (found) {
                Value old = root.value;
                root.value = value;
                return old;
            } else if (onLeftSubTree && root.left.isNil()) {
                root.setLeft(new TreeNode<>(key, value));
                size++;
                return null;
            } else if (onRightSubTree && root.right.isNil()) {
                root.setRight(new TreeNode<>(key, value));
                size++;
                return null;
            }

            root = onLeftSubTree ? root.left : root.right;
        }
    }


    @Override
    public Value delete(Key key) {
        TreeNode<Key, Value> deleted = findNode(key);
        if (deleted == null) {
            // 节点未查到
            return null;
        }

        if (deleted.left.isNil()) {
            replace(deleted, deleted.right);
        } else if (deleted.right.isNil()) {
            replace(deleted, deleted.left);
        } else {
            TreeNode<Key, Value> maxOnRight = min(deleted.right);
            if (maxOnRight != deleted.right) {
                replace(maxOnRight, maxOnRight.right);
                maxOnRight.setRight(deleted.right);
            }
            replace(deleted, maxOnRight);
            maxOnRight.setLeft(deleted.left);
        }

        size--;
        return deleted.value;
    }

    private TreeNode<Key, Value> findNode(Key key) {
        TreeNode<Key, Value> current = root;
        while (!current.isNil()) {
            int cmp = current.key.compareTo(key);
            boolean found = cmp == 0;
            boolean onLeftSubTree = cmp > 0;

            if (found) {
                return current;
            }

            current = onLeftSubTree ? current.left : current.right;
        }
        return null;
    }

    private void replace(TreeNode<Key, Value> deleted, TreeNode<Key, Value> replace) {
        if (deleted.parent.isNil()) {
            root = replace;
            replace.parent = NIL;
        } else if (deleted.onLeft()) {
            deleted.parent.setLeft(replace);
        } else {
            deleted.parent.setRight(replace);
        }
    }

    private TreeNode<Key, Value> min(TreeNode<Key, Value> currentRoot) {
        while (!currentRoot.left.isNil()) {
            currentRoot = currentRoot.left;
        }
        return currentRoot;
    }

    private TreeNode<Key, Value> max(TreeNode<Key, Value> currentRoot) {
        while (!currentRoot.right.isNil()) {
            currentRoot = currentRoot.right;
        }
        return currentRoot;
    }

    @SuppressWarnings({"rawtypes"})
    @Data
    static class TreeNode<Key extends Comparable<Key>, Value> {
        public static final TreeNode NIL = new TreeNode();

        /**
         * 父节点
         */
        @ToString.Exclude
        protected TreeNode<Key, Value> parent;

        /**
         * 左子树
         */
        protected TreeNode<Key, Value> left;

        /**
         * 右子树
         */
        protected TreeNode<Key, Value> right;
        /**
         * 树的键
         */
        protected Key key;

        /**
         * 节点值
         */
        protected Value value;

        private TreeNode() {
            this(null, null);
        }

        public TreeNode(Key key, Value value) {
            this(NIL, NIL, key, value);
        }

        public TreeNode(TreeNode<Key, Value> left, TreeNode<Key, Value> right, Key key, Value value) {
            this(NIL, left, right, key, value);
        }

        public TreeNode(TreeNode<Key, Value> parent, TreeNode<Key, Value> left, TreeNode<Key, Value> right, Key key, Value value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }

        public void setLeft(TreeNode<Key, Value> left) {
            this.left = left;
            left.parent = this;
        }

        public void setRight(TreeNode<Key, Value> right) {
            this.right = right;
            right.parent = this;
        }

        public boolean isNil() {
            return NIL == this;
        }

        public boolean onLeft() {
            return parent.left == this;
        }

        public boolean onRight() {
            return parent.right == this;
        }


        public TreeNode<Key, Value> uncle() {
            if (parent.onLeft()) {
                // 父节点在左子树
                return parent.parent.right;
            } else {
                // 父节点在右子树
                return parent.parent.left;
            }
        }

        public void rightRotate() {
            TreeNode<Key, Value> newRoot = this.getNewRoot(this.left);
            setLeft(newRoot.right);
            newRoot.setRight(this);
        }

        public void leftRotate() {
            TreeNode<Key, Value> newRoot = this.getNewRoot(this.right);
            setRight(newRoot.left);
            newRoot.setLeft(this);
        }

        private TreeNode<Key, Value> getNewRoot(TreeNode<Key, Value> newRoot) {
            if (parent.isNil()) {
                newRoot.parent = NIL;
            } else if (onLeft()) {
                parent.setLeft(newRoot);
            } else {
                parent.setRight(newRoot);
            }
            return newRoot;
        }
    }
}
