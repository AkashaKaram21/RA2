public class Main {
    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();

        for (int i = 0; i < 5; i++) {
            new Home("Home-" + i, bany).start();
        }
        for (int i = 0; i < 5; i++) {
            new Dona("Dona-" + i, bany).start();
        }
    }
}