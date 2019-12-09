package cy.stack;

import cy.array.Array;

/**
 *  基于动态数组栈
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> data;

    public ArrayStack(int capacity){
        data = new Array<>(capacity);
    }

    public ArrayStack(){
         data = new Array<>();
    }

    @Override
    public int getSize() {
        return this.data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmply();
    }

    @Override
    public void push(E o) {
        this.data.addLast(o);
    }

    @Override
    public E pop() {
        return this.data.removeLast();
    }

    @Override
    public E peek() {
        return this.data.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < data.getSize() ; i ++){
            res.append(data.get(i));
            if(i != data.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
