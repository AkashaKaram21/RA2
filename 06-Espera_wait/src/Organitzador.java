public class Organitzador {
    public static void main(String[] args) {
        System.out.println("=== Sortida esperada ===\n");

        Assistent[] assistents = new Assistent[10];
        for (int i = 0; i < 10; i++) {
            assistents[i] = new Assistent("Assistent-" + i);
        }

        for (Assistent assistent : assistents) {
            assistent.start();
        }

        for (Assistent assistent : assistents) {
            try {
                assistent.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nTotal de reserves a la llista compartida: " + Esdeveniment.getNumReservesTotal());
    }
}