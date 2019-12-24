package cy.tree.segment;

/**
 *  线段树
 *  查询和更新都是 O(logn)
 */
public class SegmentTree<E> {

    private E[] tree;

    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i< arr.length ; i ++){
            data[i] = arr[i];
        }
        // 树的容量为4倍的数组容量
        tree = (E [])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex位置创建表示区间[l, r]的线段树
    // 递归算法
    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r-l) / 2; //(l + r)/2;
        buildSegmentTree(leftTreeIndex, l ,mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 这里根据业务决定线段树的值是什么逻辑
        // 暂时为左右孩子的和
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 堆顶如果以k开头且 k = 0 的话，左孩子都是2 * k + 1
    public int leftChild(int index){
        return 2 * index + 1;
    }

    // 堆顶如果以k开头且 k = 0 的话，右孩子都是2 * k + 2
    public int rightChild(int index){
        return 2 * index + 2;
    }

    // 查询返回区间[l ,r]的值
    public E query(int ql ,int qr){
        if(ql < 0 || ql >= data.length || qr < 0 || qr >= data.length || ql > qr){
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0, 0, data.length - 1, ql, qr);
    }

    private E query(int treeIndex, int l,int r, int ql, int qr){
        if(l == ql && r == qr){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 要查的左孩子索引比中间值大，递归查询右孩子
        if(ql >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, ql, qr);
        }else if(qr <= mid){// 要查的右孩子索引比中间值小，递归查询左孩子
            return query(leftTreeIndex, l, mid, ql, qr);
        }
        E leftResult = query(leftTreeIndex, l, mid, ql, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, qr);
        return merger.merge(leftResult, rightResult);
    }

    // 将index的值更新为e
    public void set(int index, E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index ,e);
    }

    // 递归算法
    private void set(int treeIndex, int l, int r, int index, E e){
        if(l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        }else{
            set(leftTreeIndex, l , mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }





    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0;i< tree.length; i++){
            if(tree[i] != null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
        }
        res.append("]");
        return res.toString();
    }

}
