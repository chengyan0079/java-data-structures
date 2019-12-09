package cy.linkedList;

public class LinkedListTest {

    public static void main(String[] args) {


        LinkedListRecursion<Integer> linkedList = new LinkedListRecursion<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);
        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.addLast(11);
        System.out.println(linkedList);

        linkedList.remove(3);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
