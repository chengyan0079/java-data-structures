package cy.array;

/**
 * ��װ��̬����
 * ��ѯ�죬��ɾ��
 *
 */
public class Array<E> {

    /**
     *  ��������
     */
    private E[] data;

    /**
     *  ����ʹ�ø�����Ҳ������
     */
    private int size;

    /**
     *  ���ռ�Ϊ 2^10
     */
    private final int MAX_CAPACITY = 1 << 10;

    /**
     *  ���캯�����в�
     */
    public Array(int capacity){
        checkCapacity(capacity);
        this.data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     *  ���캯�����޲� Ĭ��10
     */
    public Array(){
        this(5);
    }

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for(int i = 0 ; i< arr.length; i++){
            data[i] = arr[i];
        }
        size = arr.length;
    }


    /**
     *  ��ȡ���������
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     *  У�����������Ƿ񳬹��������
     */
    private void checkCapacity(int capacity){
        if(capacity < 0 || capacity > MAX_CAPACITY){
            throw new IllegalArgumentException("������������");
        }
    }

    /**
     *  �ж�����index
     */
    private void checkIndex(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("����index����");
        }
    }

    /**
     *  �ж�����index,�������
     */
    private void checkIndexByAdd(int index){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("����index����");
        }
    }

    /**
     *  ��ȡ�������
     */
    public int getSize(){
        return  this.size;
    }

    /**
     *  �ж������Ƿ�Ϊ��
     */
    public boolean isEmply(){
        return size == 0;
    }

    /**
     * ����������һ��Ԫ��
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * ������ǰ���һ��Ԫ��
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * ������index�����һ��Ԫ��
     */
    public void add(int index,E e){
        checkIndexByAdd(index);
        if(size == data.length){
            // ����
            resize(data.length * 2);
        }
        // index֮����������ݾ������һλ��������ǰindex�ճ������Ԫ��e
        for(int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * ��������ȡֵ
     */
    public E get(int index){
        checkIndex(index);
        return data[index];
    }

    /**
     * ȡֵ���һ��Ԫ��
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * ȡֵ��һ��Ԫ��
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * ����������ֵ
     */
    public E set(int index, E e){
        checkIndex(index);
        data[index] = e;
        return get(index);
    }

    /**
     * �Ƿ����Ԫ��e
     */
    public boolean contains(E e){
        for(int i = 0; i < size ; i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * ���Ұ���Ԫ��e�������������쳣-1
     */
    public int find(E e){
        for(int i = 0; i < size ; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     *  ɾ��ĳ��������Ԫ��
     */
    public E remove(int index){
        checkIndex(index);
        E e = data[index];
        //��index֮����������ݾ���ǰ��һλ��������ǰindexԪ�ر�����
        for(int i = index + 1 ; i < size ; i ++) {
            data[i - 1] = data[i];
        }
        size --;
        // ����
        if(size == data.length / 3 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return e;
    }

    /**
     *  ɾ�����һ��Ԫ��
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     *  ɾ����һ��Ԫ��
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     *  ɾ��Ԫ��e
     */
    public int removeElement(E e){
        int index = find(e);
        if(index != -1) {
            remove(index);
            return index;
        }
        return -1;
    }

    /**
     *  ���ݣ�����
     */
    private void resize(int capacity){
        E[] newArr = (E[]) new Object[capacity];
        for(int i = 0;i < size;i ++){
            newArr[i] = data[i];
        }
        data = newArr;

    }

    /**
     *  ������������λ��
     */
    public void swap(int i, int j){

        if(i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * �ַ���
     */
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("�������size = %d����������capacity = %d\n", size, data.length));
        sb.append("[");
        if(!isEmply()){
            for(int i=0; i<size; i++){
                sb.append(data[i]);
                if(i != size -1){
                    sb.append(", ");
                }
            }
        }
        sb.append("]\n");
        sb.append("-------------------------------");
        return sb.toString();
    }

}
