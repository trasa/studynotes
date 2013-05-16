package com.meancat.study.datastructures;

public class BinaryTree<T extends Comparable<? super T>>  {

    private TreeNode<T> root;

    public BinaryTree(T rootData) {
        root = new TreeNode<T>(rootData);
    }

    public void add(T data) {
        root.add(data);
    }

    public void remove(T data) {
        root.remove(data);
    }

    @Override
    public String toString() {
        return "BinaryTree{" + root + '}';
    }

    public String preorder() {
        return root == null ? "null" : root.preorder();
    }

    public class TreeNode<T extends Comparable<? super T>> {
        private TreeNode<T> left;
        private TreeNode<T> right;

        private final T data;

        public TreeNode(T data) {
            this.data = data;
        }

        public void add(T value) {
            if (value.compareTo(data) <= 0) {
                if (left == null) {
                    left = new TreeNode<T>(value);
                } else {
                    left.add(value);
                }
            } else {
                if (right == null) {
                    right = new TreeNode<T>(value);
                } else {
                    right.add(value);
                }
            }
        }

        public void remove(T value) {

        }

        @Override
        public String toString() {
            return "{" + data + "|" + (left == null ? "null" : left.toString()) +
                    "|" + (right == null ? "null" : right.toString()) + "}";
        }

        public String preorder() {
            return data + " "
                    + (left == null ? "null" : left.preorder()) + " "
                    + (right == null ? "null" : right.preorder());
        }
    }
}
