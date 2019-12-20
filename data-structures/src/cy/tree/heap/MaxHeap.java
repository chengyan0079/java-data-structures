package cy.tree.heap;

import cy.array.Array;

/**
 *  �󶥶�
 *  ����һ����ȫ������
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
    // ��������Ԫ��
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    // �ϸ���������ǰ�����븸��������λ��
    private void siftUp(int k){
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // �����е����Ԫ�أ����Ǹ��ڵ�Ԫ��
    public E findMax(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    // ȡ���������Ԫ�أ��󶥶�ֻ��ȥ�����ڵ�Ԫ��
    public E extractMax(){
        // �ҵ����Ԫ��
        E ret = findMax();
        // �����һ��������Ԫ�غ͸��ڵ�Ԫ�ؽ���λ��
        data.swap(0, data.getSize() - 1);
        // ��ʱ���Ԫ�������ɾ������
        data.removeLast();
        // �³�������ά���󶥶�����
        siftDown(0);
        return ret;
    }

    // �³�����
    // k���ڽڵ��Ԫ��ֵ �� ��������������������ֵ���бȽϣ����ĺ��ӽڵ� �� ��ǰ�ڵ㽻��λ�ã������Ƚ�
    private void siftDown(int k){
        // ����k�ڵ�����ӵ����� �� ��������Ƚ�
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k); // �ڴ���ѭ����,data[k]��data[j]����λ��
            // j + 1 Ϊ�Һ�������
            if( j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0 ){
                j ++; // �൱�� j = rightChild(k);
            }
            // data[j] �� leftChild �� rightChild �е����ֵ
            if(data.get(k).compareTo(data.get(j)) >= 0 ) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

}
