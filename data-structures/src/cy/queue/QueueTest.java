package cy.queue;

public class QueueTest {

    public static void main(String[] args) {

//        ArrayQueue<String> as = new ArrayQueue<>();
//        as.enqueue("13");
//        as.enqueue("14");
//        as.enqueue("15");
//        System.out.println(as);
//        System.out.println("as.getSize() == " + as.getSize());
//        as.dequeue();
//        System.out.println("as.dequeue() == " + as);
//        System.out.println("as.getFront() == " + as.getFront());
//        as.enqueue("16");
//        System.out.println(as);
//        System.out.println("===========================");
//        LoopQueue<Integer> queue = new LoopQueue<>();
//        for(int i = 0 ; i < 15 ; i ++){
//            queue.enqueue(i);
//            System.out.println(queue);
//
//            if(i % 3 == 2){
//                queue.dequeue();
//                System.out.println(queue);
//            }
//        }

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }


    }
}
