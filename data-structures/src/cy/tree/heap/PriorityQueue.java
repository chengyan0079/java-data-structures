package cy.tree.heap;

import cy.queue.Queue;

/**
 *  以堆为基本的优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> heap;

    public PriorityQueue(){
        heap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.extractMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }
}
