package cy.stack;

public class StackTest {

    public static void main(String[] args) {

//        ArrayStack<String> as = new ArrayStack<>();
//        as.push("13");
//        as.push("14");
//        as.push("15");
//        System.out.println(as);
//        System.out.println("as.getSize() == " + as.getSize());
//        as.pop();
//        System.out.println("as.pop() == " + as);
//        System.out.println("as.peek() == " + as.peek());
//        as.push("17");
//        System.out.println(as);

        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

    }
}
