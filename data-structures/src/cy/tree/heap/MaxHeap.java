package cy.tree.heap;

import cy.array.Array;

/**
 *  大顶堆
 *  他是一个完全二叉树
 *
 *  父结点值总是大于等左右孩子的值
 */
public class MaxHeap<E extends Comparable<E>>  {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<E>(capacity);
    }

    public MaxHeap(){
        data = new Array<E>();
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmply();
    }

    // 当前索引所在元素的父元素的索引
    // 根节点的索引为0，根节点没有夫节点
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        // 返回值取整数
        return (index - 1) / 2;
    }

    // 当前节点元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 当前节点元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

}
