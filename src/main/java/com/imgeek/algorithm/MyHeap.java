package com.imgeek.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * auth:    xiemin
 * date:    2018-08-05
 * desc:    堆
 */

@FunctionalInterface
interface MyComparable<T> {
    boolean compare(T o1, T o2);
}

@Slf4j
public class MyHeap<T> {
    private T[] heap;

    private MyComparable<T> comparable;

    public MyHeap(MyComparable<T> comparable) {
        this.comparable = comparable;
    }

    public T get(int index) {
        return index < size() ? heap[index] : null;
    }

    public T heapRoot() {
        return heap[heapRootIndex()];
    }

    public int heapRootIndex() {
        return size() > 0 ? 0 : -1;
    }

    public T heapTail() {
        return heap[heap.length - 1];
    }

    public int heapTailIndex() {
        return size() > 0 ? heap.length - 1 : -1;
    }

    public int size() {
        if (heap != null && heap.length > 0) {
            return heap.length;
        }
        log.debug("heap is null or heap size is zero");
        return 0;
    }

    public int childLeftIndex(int index) {
        int newIndex = index * 2 + 1;
        if (newIndex >= size()) {
            return -1;
        }
        return newIndex;
    }

    public T childLeft(int index) {
        int leftIndex = childLeftIndex(index);
        return leftIndex != -1 ? heap[leftIndex] : null;
    }

    public int childRightIndex(int index) {
        int newIndex = index * 2 + 2;
        if (newIndex >= size()) {
            return -1;
        }
        return newIndex;
    }

    public T childRight(int index) {
        int rightIndex = childRightIndex(index);
        return rightIndex != -1 ? heap[rightIndex] : null;
    }

    public int parentIndex(int index) {
        int newIndex = (index - 1) / 2;
        if (newIndex < 0 || newIndex >= size() || index == 0) {
            return -1;
        }
        return newIndex;
    }

    public T parent(int index) {
        int parentIndex = (index - 1) / 2;
        return parentIndex != -1 ? heap[parentIndex] : null;
    }

    private void recurParentExchangeBetweenParentAndChild(int parentIndex, int childIndex) {
        if (parentIndex != parentIndex(childIndex)
                && childIndex != childLeftIndex(parentIndex)
                && childIndex != childRightIndex(parentIndex)) {
            log.debug("relation of parent and child is invaild");
            return;
        }

        if (checkOverLimit(parentIndex) || checkOverLimit(childIndex)) {
            log.debug("arr over limit");
            return;
        }

        if (comparable.compare(heap[parentIndex], heap[childIndex])) {
            changeData(parentIndex, childIndex);
        }

        int newParentIndex = parentIndex(parentIndex);
        int newChildIndex = parentIndex;
        recurParentExchangeBetweenParentAndChild(newParentIndex, newChildIndex);
    }

    private enum RecurDirect {
        LEFT, RIGHT
    }

    private void recurChildExchangeBetweenParentAndChild(int parentIndex) {
        T current = get(parentIndex);
        //获取左子树
        int childLeftIndex = childLeftIndex(parentIndex);
        T childLeft = childLeft(parentIndex);

        //获取右子树
        int childRightIndex = childRightIndex(parentIndex);
        T childRight = childRight(parentIndex);

        RecurDirect leftOrRigth;
        if (childLeft == null && childRight == null) {
            return;
        } else if (childRight == null) {
            leftOrRigth = RecurDirect.LEFT;
        } else {
            if (comparable.compare(childLeft, childRight)) {
                leftOrRigth = RecurDirect.RIGHT;
            } else {
                leftOrRigth = RecurDirect.LEFT;
            }
        }

        if (leftOrRigth == RecurDirect.LEFT && comparable.compare(current, childLeft)) {
            changeData(parentIndex, childLeftIndex);
            recurChildExchangeBetweenParentAndChild(childLeftIndex);
        }

        if (leftOrRigth == RecurDirect.RIGHT && comparable.compare(current, childRight)) {
            changeData(parentIndex, childRightIndex);
            recurChildExchangeBetweenParentAndChild(childRightIndex);
        }
    }

    /**
     * 插入新数据，重新调整堆
     * insert data
     *
     * @param data
     */
    public void insertNode(T data) {
        if (size() > 0) {
            int newSize = heap.length + 1;
            int lastIndex = newSize - 1;
            int parentOfLastestIndex = parentIndex(lastIndex);
            heap = Arrays.copyOf(heap, heap.length + 1);
            heap[lastIndex] = data;
            recurParentExchangeBetweenParentAndChild(parentOfLastestIndex, lastIndex);
        } else {
            heap = (T[]) new Object[1];
            heap[0] = data;
        }
    }

    public T extract() {
        T root = heapRoot();
        int rootIndex = heapRootIndex();
        int tailIndex = heapTailIndex();
        changeData(rootIndex, tailIndex);
        /**
         * 清除最后一个叶子节点
         */
        heap = Arrays.copyOf(heap, heap.length - 1);
        /**
         * 重新调整堆
         */
        recurChildExchangeBetweenParentAndChild(rootIndex);
        log.debug("root: ".concat(String.valueOf(root)));
        return root;
    }

    private int changeData(int idx1, int idx2) {
        if (checkOverLimit(idx1) || checkOverLimit(idx2)) {
            log.debug("arr over limit");
            return -1;
        }
        T o = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = o;
        return 0;
    }

    /**
     * 判断索引是否越界
     *
     * @param index
     * @return
     */
    private boolean checkOverLimit(int index) {
        return (index == -1 || index >= size()) ? true : false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size(); i++) {
            sb.append(heap[i]);
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
