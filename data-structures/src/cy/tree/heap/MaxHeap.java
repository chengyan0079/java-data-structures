package cy.tree.heap;

import cy.array.Array;

/**
 *  大顶堆
 *  他是一个完全二叉树
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
    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    // 上浮操作。当前索引与父索引交换位置
    private void siftUp(int k){
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素，就是根节点元素
    public E findMax(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    // 取出堆中最大元素，大顶堆只能去除根节点元素
    public E extractMax(){
        // 找到最大元素
        E ret = findMax();
        // 将最后一个索引的元素和根节点元素交换位置
        data.swap(0, data.getSize() - 1);
        // 此时最大元素在最后，删除操作
        data.removeLast();
        // 下沉操作，维护大顶堆性质
        siftDown(0);
        return ret;
    }

    // 下沉操作
    // k所在节点的元素值 与 它的左右两孩子中最大的值进行比较，最大的孩子节点 与 当前节点交换位置，继续比较
    private void siftDown(int k){
        // 索引k节点的左孩子的索引 和 数组个数比较
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            // j + 1 为右孩子索引
            if( j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0 ){
                j ++; // 相当与 j = rightChild(k);
            }
            // data[j] 是 leftChild 和 rightChild 中的最大值
            if(data.get(k).compareTo(data.get(j)) >= 0 ) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

}
