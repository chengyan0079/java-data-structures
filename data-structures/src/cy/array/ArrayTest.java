package cy.array;

public class ArrayTest {

    public static void main(String[] args) {

//        List<String> l = new ArrayList<>();
        Array<String> arr = new Array<>();
        arr.addLast("10");
        arr.addLast("11");
        arr.addLast("12");
        arr.addLast("14");
        System.out.println("arr.addLast() == " + arr);
        arr.add(2,"13");
        System.out.println("arr.add(2,13) == " + arr);
        arr.addFirst("16");
        System.out.println("arr.addFirst(16) == " + arr);
        System.out.println("arr.find(11) == " + arr.find("11"));
        System.out.println("arr.contains(13) == " + arr.contains("13"));
        System.out.println("arr.get(4) == " + arr.get(4));
        arr.set(3,"15");
        System.out.println("arr.set(3,15) == " + arr);
        arr.remove(2);
        System.out.println("arr.remove(2) == " + arr);
        arr.removeFirst();
        System.out.println("arr.removeFirst() == " + arr);
        arr.removeLast();
        System.out.println("arr.removeLast() == " + arr);
        arr.removeElement("9");
        System.out.println("arr.removeElement(9) == " + arr);

    }
}
