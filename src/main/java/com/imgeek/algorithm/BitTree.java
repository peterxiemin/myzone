package com.imgeek.algorithm;

/**
 * @author: xiemin
 * @date: 2018-09-05
 * @desc: 二叉树
 */

import java.util.Stack;

/**
 * 二叉树中的节点定义
 *
 * @param <T>
 */
class BitNode<T> {
    private T data;
    private BitNode<T> left;
    private BitNode<T> right;

    public BitNode<T> getLeft() {
        return left;
    }

    public void setLeft(BitNode<T> left) {
        this.left = left;
    }

    public BitNode<T> getRight() {
        return right;
    }

    public void setRight(BitNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

/**
 * 通过数组构建二叉树
 *
 * @param <T>
 */
class BitTreeUtil<T> {
    public BitTree<T> bitTreeMerge(BitTree<T> bitTreeLeft, BitTree<T> bitTreeRight) {
        BitTree<T> bitTree = new BitTree<>();
        bitTree.insertLeftNode(bitTree.bitTreeRoot(), bitTreeLeft.bitTreeRoot());
        bitTree.insertRightNode(bitTree.bitTreeRoot(), bitTreeRight.bitTreeRoot());
        return bitTree;
    }

    public BitTree<T> createBitTreeFromArray(T[] arr) {
        BitTree<T> bitTree = new BitTree<T>(arr[0]);
        createSingleBitTree(bitTree, bitTree.bitTreeRoot(), arr, 0);
        return bitTree;
    }

    public void createSingleBitTree(BitTree<T> bitTree, BitNode<T> parentNode, T[] arr, int index) {
        BitNode<T> leftNode;
        BitNode<T> rightNode;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int edge = arr.length - 1;
        if (leftIndex <= edge) {
            leftNode = new BitNode<>();
            leftNode.setData(arr[leftIndex]);
            bitTree.insertLeftNode(parentNode, leftNode);
            createSingleBitTree(bitTree, leftNode, arr, leftIndex);
        }
        if (rightIndex <= edge) {
            rightNode = new BitNode<>();
            rightNode.setData(arr[rightIndex]);
            bitTree.insertRightNode(parentNode, rightNode);
            createSingleBitTree(bitTree, rightNode, arr, rightIndex);
        }
    }
}

/**
 * 二叉树定义
 *
 * @param <T>
 */
public class BitTree<T> {
    private static int size = 0;
    /**
     * 二叉树根节点
     */
    BitNode<T> head;

    /**
     * 二叉树无参数构造函数
     */
    public BitTree() {
        BitNode<T> bitNode = new BitNode<>();
        setHead(bitNode);
    }

    /**
     * 二叉树有参数构造函数
     *
     * @param data
     */
    public BitTree(T data) {
        BitNode<T> bitNode = new BitNode<>();
        bitNode.setData(data);
        setHead(bitNode);
    }

    public BitNode<T> getHead() {
        return head;
    }

    private void setHead(BitNode<T> head) {
        this.head = head;
    }

    public int insertLeftNode(BitNode<T> parentNode, BitNode<T> childNode) {
        parentNode.setLeft(childNode);
        return 0;
    }

    public int insertRightNode(BitNode<T> parentNode, BitNode<T> childNode) {
        parentNode.setRight(childNode);
        return 0;
    }

    public int removeLeftNode(BitNode<T> parentNode) {
        parentNode.setLeft(null);
        return 0;
    }

    public int removeRightNode(BitNode<T> parentNode) {
        parentNode.setRight(null);
        return 0;
    }

    public boolean isLeafOfBitTree(BitNode<T> bitNode) {
        return bitNode.getLeft() == null && bitNode.getRight() == null ? true : false;
    }

    public BitNode<T> bitTreeRoot() {
        return this.getHead();
    }

    public BitNode<T> getLeftNode(BitNode<T> parentNode) {
        return parentNode.getLeft();
    }

    public BitNode<T> getRightNode(BitNode<T> parentNode) {
        return parentNode.getRight();
    }

    public int bitTreeSize() {
        size = 0;
        preOrderTraverse(bitTreeRoot());
        return size;
    }

    /**
     * 二叉树先序递归遍历
     *
     * @param bitNode
     */
    public void preOrderTraverse(BitNode<T> bitNode) {
        size++;
        if (isLeafOfBitTree(bitNode)) return;
        if (bitNode.getLeft() != null)
            preOrderTraverse(bitNode.getLeft());
        if (bitNode.getRight() != null)
            preOrderTraverse(bitNode.getRight());
    }

    /**
     * 二叉树非递归中序遍历
     *
     * @param bitNode
     */
    public String InOrderTraverseUsingStack(BitNode<T> bitNode) {
        String result = "";
        Stack<BitNode> stack = new Stack<BitNode>();
        if (bitNode == null)
            return "";
        stack.push(bitNode);

        BitNode cur = bitNode.getLeft();
        while (cur != null) {
            stack.push(cur);
            cur = cur.getLeft();
        }

        while (!stack.isEmpty()) {
            cur = stack.pop();
            result += visit(cur);
            cur = cur.getRight();
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }
        }
        return result;
    }

    /**
     * 二叉树遍历时对每个节点的操作
     */
    private String visit(BitNode<T> bitNode) {
        return bitNode.getData() + " ";
    }
}
