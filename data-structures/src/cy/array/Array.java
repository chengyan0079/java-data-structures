package cy.array;

/**
 * 封装动态数组
 * 查询快，增删慢
 *
 */
public class Array<E> {

    /**
     *  数组数据
     */
    private E[] data;

    /**
     *  数组使用个数，也是索引
     */
    private int size;

    /**
     *  最大空间为 2^10
     */
    private final int MAX_CAPACITY = 1 << 10;

    /**
     *  构造函数，有参
     */
    public Array(int capacity){
        checkCapacity(capacity);
        this.data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     *  构造函数，无参 默认10
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
     *  获取数组的容量
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     *  校验申请容量是否超过最大容量
     */
    private void checkCapacity(int capacity){
        if(capacity < 0 || capacity > MAX_CAPACITY){
            throw new IllegalArgumentException("申请容量错误！");
        }
    }

    /**
     *  判断索引index
     */
    private void checkIndex(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("索引index错误！");
        }
    }

    /**
     *  判断索引index,用于添加
     */
    private void checkIndexByAdd(int index){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("索引index错误！");
        }
    }

    /**
     *  获取数组个数
     */
    public int getSize(){
        return  this.size;
    }

    /**
     *  判断数组是否为空
     */
    public boolean isEmply(){
        return size == 0;
    }

    /**
     * 数组最后添加一个元素
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 数组最前添加一个元素
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 在索引index处添加一个元素
     */
    public void add(int index,E e){
        checkIndexByAdd(index);
        if(size == data.length){
            // 扩容
            resize(data.length * 2);
        }
        // index之后的所有数据均向后移一位索引，则当前index空出来添加元素e
        for(int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 按照索引取值
     */
    public E get(int index){
        checkIndex(index);
        return data[index];
    }

    /**
     * 取值最后一个元素
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 取值第一个元素
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 按照索引赋值
     */
    public E set(int index, E e){
        checkIndex(index);
        data[index] = e;
        return get(index);
    }

    /**
     * 是否包含元素e
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
     * 查找包含元素e，返回索引，异常-1
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
     *  删除某个索引的元素
     */
    public E remove(int index){
        checkIndex(index);
        E e = data[index];
        //　index之后的所有数据均向前移一位索引，则当前index元素被覆盖
        for(int i = index + 1 ; i < size ; i ++) {
            data[i - 1] = data[i];
        }
        size --;
        // 缩容
        if(size == data.length / 3 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return e;
    }

    /**
     *  删除最后一个元素
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     *  删除第一个元素
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     *  删除元素e
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
     *  扩容，缩容
     */
    private void resize(int capacity){
        E[] newArr = (E[]) new Object[capacity];
        for(int i = 0;i < size;i ++){
            newArr[i] = data[i];
        }
        data = newArr;

    }

    /**
     *  交换两个索引位置
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
     * 字符串
     */
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("数组个数size = %d，数组容量capacity = %d\n", size, data.length));
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
