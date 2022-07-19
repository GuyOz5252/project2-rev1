package com.example.project2_rev1;

public class BinaryTree<T> {

    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(BinaryTree<T> left, T value, BinaryTree<T> right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }

    public BinaryTree(T value) {
        this.left = null;
        this.value = value;
        this.right = null;
    }

    public BinaryTree() {
        this.left = null;
        this.right = null;
    }

    public T getValue() {
        return this.value;
    }

    public BinaryTree<T> getLeft() {
        return this.left;
    }

    public BinaryTree<T> getRight() {
        return this.right;
    }

    public boolean hasLeft() {
        return (left != null);
    }

    public boolean hasRight() {
        return (right != null);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTree<T> right) {
        this.right = right;
    }
}
