package com.test;

import lombok.Data;

/**
 * @author yang_tao@<yangtao.letzgo.com.cn>
 * @version 1.0
 * @date 2018-03-29 14:48
 */
@Data
public class BinaryTreeNode<E> {

    private E val;

    private BinaryTreeNode<E> left;

    private BinaryTreeNode<E> right;

    public BinaryTreeNode(E val) {
        this.val = val;
    }

    public BinaryTreeNode(E val, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static void main(String[] args) {
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(3);

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, left, right);

        BinaryTreeNode<Integer> left2 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> right2 = new BinaryTreeNode<>(5);
        left.setLeft(left2);
        left.setRight(right2);
        System.out.println(root);
    }
}
