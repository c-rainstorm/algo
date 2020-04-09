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
                    leftRotate(node);
                }

                // 左左
                // node 为最下面的一个节点
                node.parent.color = RBTreeNode.Color.BLACK;
                node.parent.parent.color = RBTreeNode.Color.RED;
                rightRotate(node.parent.parent);
            } else {
                if (node.onLeft()) {
                    // 右左
                    node = node.parent;
                    rightRotate(node);
                }

                // 右右
                // node 为最下面的一个节点
                node.parent.color = RBTreeNode.Color.BLACK;
                node.parent.parent.color = RBTreeNode.Color.RED;
                leftRotate(node.parent.parent);
            }
        }

        // 重置根节点为黑色
        root.toBlack();
    }

    @Override
    public Value delete(Key key) {
        RBTreeNode<Key, Value> deleted = findNode(key);
        if (deleted == null) {
            // 节点未查到
            return null;
        }

        boolean needFix = deleted.isBlack();
        RBTreeNode<Key, Value> focus;
        if (deleted.left.isNil()) {
            focus = deleted.right;
            replace(deleted, deleted.right);
        } else if (deleted.right.isNil()) {
            focus = deleted.left;
            replace(deleted, deleted.left);
        } else {
            RBTreeNode<Key, Value> minOnRight = min(deleted.right);

            needFix = minOnRight.isBlack();
            focus = minOnRight.right;
            if (minOnRight != deleted.right) {
                replace(minOnRight, minOnRight.right);
                minOnRight.setRight(deleted.right);
            } else {
                // 解决 focus 为 NIL 的情况
                focus.parent = minOnRight;
            }
            replace(deleted, minOnRight);
            minOnRight.setLeft(deleted.left);
            minOnRight.color = deleted.color;
        }

        if (needFix) {
            deleteFix(focus);
        }

        size--;
        return deleted.value;
    }

    private RBTreeNode<Key, Value> findNode(Key key) {
        RBTreeNode<Key, Value> current = root;
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

    private void replace(RBTreeNode<Key, Value> deleted, RBTreeNode<Key, Value> replace) {
        if (deleted.parent.isNil()) {
            root = replace;
            replace.parent = RBTreeNode.NIL;
        } else if (deleted.onLeft()) {
            deleted.parent.setLeft(replace);
        } else {
            deleted.parent.setRight(replace);
        }
    }

    private void deleteFix(RBTreeNode<Key, Value> focus) {
        // 将额外黑色上移至 root，已使黑高不再影响
        while (focus != root && focus.isBlack()) {
            RBTreeNode<Key, Value> focusParent = focus.parent;
            if (focus.onLeft()) {
                RBTreeNode<Key, Value> brother = focusParent.right;
                if (brother.isRed()) {
                    // 把兄弟节点搞成黑色
                    brother.toBlack();
                    focusParent.toRed();
                    leftRotate(focusParent);
                    brother = focusParent.right;
                }

                if (brother.left.isBlack() && brother.right.isBlack()) {
                    // 兄弟节点三黑，上提关注节点
                    brother.toRed();
                    focus = focusParent;
                    continue;
                }

                if (brother.right.isBlack()) {
                    // 左红右黑
                    brother.left.toBlack();
                    brother.toRed();
                    rightRotate(brother);
                    brother = focusParent.right;
                }
                brother.color = focusParent.color;
                focusParent.toBlack();
                brother.right.toBlack();
                leftRotate(focusParent);
            } else {
                RBTreeNode<Key, Value> brother = focusParent.left;
                if (brother.isRed()) {
                    // 把兄弟节点搞成黑色
                    brother.toBlack();
                    focusParent.toRed();
                    rightRotate(focusParent);
                    brother = focusParent.left;
                }

                if (brother.left.isBlack() && brother.right.isBlack()) {
                    // 兄弟节点三黑，上提关注节点
                    brother.toRed();
                    focus = focusParent;
                    continue;
                }

                if (brother.left.isBlack()) {
                    // 左红右黑
                    brother.right.toBlack();
                    brother.toRed();
                    leftRotate(brother);
                    brother = focusParent.left;
                }
                brother.color = focusParent.color;
                focusParent.toBlack();
                brother.left.toBlack();
                rightRotate(focusParent);
            }

            break;
        }

        focus.toBlack();
    }

    private void leftRotate(RBTreeNode<Key, Value> node) {
        RBTreeNode<Key, Value> newRoot = node.getNewRoot(node.right);
        node.setRight(newRoot.left);
        newRoot.setLeft(node);
        if (node == root) {
            root = newRoot;
        }
    }

    public void rightRotate(RBTreeNode<Key, Value> node) {
        RBTreeNode<Key, Value> newRoot = node.getNewRoot(node.left);
        node.setLeft(newRoot.right);
        newRoot.setRight(node);
        if (node == root) {
            root = newRoot;
        }
    }

    private RBTreeNode<Key, Value> min(RBTreeNode<Key, Value> currentRoot) {
        while (!currentRoot.left.isNil()) {
            currentRoot = currentRoot.left;
        }
        return currentRoot;
    }

//    private RBTreeNode<Key, Value> max(RBTreeNode<Key, Value> currentRoot) {
//        while (!currentRoot.right.isNil()) {
//            currentRoot = currentRoot.right;
//        }
//        return currentRoot;
//    }

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

        public boolean isBlack() {
            return Color.BLACK.equals(color);
        }

        public boolean onLeft() {
            return parent.left == this;
        }

        public boolean onRight() {
            return parent.right == this;
        }

        public void toRed() {
            this.color = Color.RED;
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
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
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
