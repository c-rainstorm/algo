package me.rainstorm.algo.ds.tree;

import lombok.Data;
import lombok.ToString;

import static me.rainstorm.algo.ds.tree.RedBlackTree.RBTreeNode.NIL;

/**
 * @author baochen1.zhang
 * @date 2020.04.07
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> extends AbstractSearchTree<Key, Value> {
    protected RBTreeNode<Key, Value> root = NIL;

    @Override
    public Value put(Key key, Value value) {
        RBTreeNode<Key, Value> node = new RBTreeNode<>(key, value);
        Value old = doPut(node);

        insertFix(node);
        return old;
    }

    @Override
    public Value delete(Key key) {
        return null;
    }

    private Value doPut(RBTreeNode<Key, Value> node) {
        if (root.isNil()) {
            root = node;
            return null;
        }

        Key targetKey = node.getKey();
        RBTreeNode<Key, Value> parent = root;

        while (true) {
            int cmp = parent.key.compareTo(targetKey);
            boolean found = cmp == 0;
            boolean onLeftSubTree = cmp > 0;
            boolean onRightSubTree = cmp < 0;

            if (found) {
                Value old = parent.value;
                parent.value = node.value;
                return old;
            } else if (onLeftSubTree && parent.left.isNil()) {
                parent.setLeft(node);
                size++;
                return null;
            } else if (onRightSubTree && parent.right.isNil()) {
                parent.setRight(node);
                size++;
                return null;
            }

            parent = onLeftSubTree ? parent.left : parent.right;
        }
    }

    private void insertFix(RBTreeNode<Key, Value> node) {
        assert node.isRed();
        assert this.root != NIL;

        while (node.parent.isRed()) {
            // 父节点是祖父节点的左子树
            RBTreeNode<Key, Value> uncle = node.uncle();
            if (uncle.isRed()) {
                // 左子树和右子树都是红色
                // 祖父节点染红，父节点和叔叔节点染黑
                node = node.parent.parent;
                node.renderRed();
                continue;
            }

            if (node.parent.onLeft()) {
                if (node.onRight()) {
                    // 左右
                    node = node.parent;
                    node.leftRotate();
                }

                // 左左
                // node 为最下面的一个节点
                node.parent.color = RBTreeNode.Color.BLACK;
                node.parent.parent.color = RBTreeNode.Color.RED;
                node.parent.parent.rightRotate();
            } else {
                if (node.onLeft()) {
                    // 右左
                    node = node.parent;
                    node.rightRotate();
                }

                // 右右
                // node 为最下面的一个节点
                node.parent.color = RBTreeNode.Color.BLACK;
                node.parent.parent.color = RBTreeNode.Color.RED;
                node.parent.parent.leftRotate();
            }
        }

        while (!node.parent.isNil()) {
            node = node.parent;
        }

        root = node;
        // 重置根节点为黑色
        root.setColor(RBTreeNode.Color.BLACK);
    }

    @SuppressWarnings({"rawtypes"})
    @Data
    static class RBTreeNode<Key extends Comparable<Key>, Value> {
        public static final RBTreeNode NIL = new RBTreeNode();

        /**
         * 父节点
         */
        @ToString.Exclude
        protected RBTreeNode<Key, Value> parent;

        /**
         * 左子树
         */
        protected RBTreeNode<Key, Value> left;

        /**
         * 右子树
         */
        protected RBTreeNode<Key, Value> right;
        /**
         * 树的键
         */
        protected Key key;

        /**
         * 节点值
         */
        protected Value value;

        /**
         * 节点颜色
         */
        protected Color color = Color.RED;

        private RBTreeNode() {
            this(null, null);
            this.color = Color.BLACK;
        }

        public RBTreeNode(Key key, Value value) {
            this(NIL, NIL, key, value);
        }

        public RBTreeNode(RBTreeNode<Key, Value> left, RBTreeNode<Key, Value> right, Key key, Value value) {
            this(NIL, left, right, key, value);
        }

        public RBTreeNode(RBTreeNode<Key, Value> parent, RBTreeNode<Key, Value> left, RBTreeNode<Key, Value> right, Key key, Value value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }

        public void setLeft(RBTreeNode<Key, Value> left) {
            this.left = left;
            left.parent = this;
        }

        public void setRight(RBTreeNode<Key, Value> right) {
            this.right = right;
            right.parent = this;
        }

        public boolean isNil() {
            return NIL == this;
        }

        public boolean isRed() {
            return Color.RED.equals(color);
        }

        public boolean onLeft() {
            return parent.left == this;
        }

        public boolean onRight() {
            return parent.right == this;
        }

        public void toBlack() {
            this.color = Color.BLACK;
        }

        public RBTreeNode<Key, Value> renderRed() {
            this.color = Color.RED;
            left.color = Color.BLACK;
            right.color = Color.BLACK;
            return this;
        }

        public RBTreeNode<Key, Value> uncle() {
            if (parent.onLeft()) {
                // 父节点在左子树
                return parent.parent.right;
            } else {
                // 父节点在右子树
                return parent.parent.left;
            }
        }

        public void rightRotate() {
            RBTreeNode<Key, Value> newRoot = this.getNewRoot(this.left);
            setLeft(newRoot.right);
            newRoot.setRight(this);
        }

        public void leftRotate() {
            RBTreeNode<Key, Value> newRoot = this.getNewRoot(this.right);
            setRight(newRoot.left);
            newRoot.setLeft(this);
        }

        private RBTreeNode<Key, Value> getNewRoot(RBTreeNode<Key, Value> newRoot) {
            if (parent.isNil()) {
                newRoot.parent = NIL;
            } else if (onLeft()) {
                parent.setLeft(newRoot);
            } else {
                parent.setRight(newRoot);
            }
            return newRoot;
        }

        enum Color {
            RED, BLACK
        }
    }
}
