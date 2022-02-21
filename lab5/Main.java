import managers.Interpreter;

public class Main {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        String file_name = System.getenv("FILE");



        interpreter.loadCollectionFromFile(file_name);
        interpreter.run();

    }
}
