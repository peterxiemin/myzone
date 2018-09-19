package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;
/**
 * @author :xiemin
 * @date: 2018-09-19
 */
import static org.junit.Assert.*;

public class BitTreeTest {

    private BitTree<Integer> bitTree;
    private BitTree<Integer> bitTree1;
    private BitTree<Integer> bitTree2;

    private BitTreeUtil<Integer> bitTreeUtil;
    private static int SIZE = 12;

    @Before
    public void setUp() {
        bitTree = new BitTree<>();
        bitTree1 = new BitTree<>();
        bitTree2 = new BitTree<>();

        bitTreeUtil = new BitTreeUtil<>();
        //将数组转化为左平衡二叉树
        //左平衡树(堆) 数字i, 父节点: (i-1)/2, 左孩子：2i+1 右孩子: 2i+2
        Integer[] arr = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Integer[] arr1 = new Integer[]{0, 1, 2, 3};
        Integer[] arr2 = new Integer[]{4, 5, 6, 7, 8, 9, 10};
        bitTree = bitTreeUtil.createBitTreeFromArray(arr);
        bitTree1 = bitTreeUtil.createBitTreeFromArray(arr1);
        bitTree2 = bitTreeUtil.createBitTreeFromArray(arr2);
    }

    @Test
    public void testBitTreeSize() {
        assertEquals(SIZE, bitTree.bitTreeSize());
    }

    @Test
    public void bitTreeMerge() {
        assertEquals(4, bitTree1.bitTreeSize());
        assertEquals(7, bitTree2.bitTreeSize());
        BitTree<Integer> bitTree3 = bitTreeUtil.bitTreeMerge(bitTree1, bitTree2);
        assertEquals(SIZE, bitTree3.bitTreeSize());
    }

    @Test
    public void testInOrderTraverseUsingStack() {
        assertEquals("7 3 8 1 9 4 10 0 11 5 2 6 ", bitTree.InOrderTraverseUsingStack(bitTree.getHead()));
        assertEquals("3 1 0 2 ", bitTree1.InOrderTraverseUsingStack(bitTree1.getHead()));
        assertEquals("7 5 8 4 9 6 10 ", bitTree2.InOrderTraverseUsingStack(bitTree2.getHead()));
    }


}