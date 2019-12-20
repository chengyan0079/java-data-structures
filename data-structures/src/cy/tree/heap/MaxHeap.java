package cy.tree.heap;

import cy.array.Array;

/**
 *  �󶥶�
 *  ����һ����ȫ������
 *
 *  �����ֵ���Ǵ��ڵ����Һ��ӵ�ֵ
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

    // ��ǰ��������Ԫ�صĸ�Ԫ�ص�����
    // ���ڵ������Ϊ0�����ڵ�û�з�ڵ�
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        // ����ֵȡ����
        return (index - 1) / 2;
    }

    // ��ǰ�ڵ�Ԫ�ص����ӽڵ������
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // ��ǰ�ڵ�Ԫ�ص��Һ��ӽڵ������
    private int rightChild(int index){
        return index * 2 + 2;
    }

}
