//private ArrayList<String> refTable = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
public class Main {
    public static void main(String[] args) {
        EnAndDec test = new EnAndDec();
        System.out.println("Decoded text is "+test.decode(test.encode("HELLO WORLD")));
    }
}