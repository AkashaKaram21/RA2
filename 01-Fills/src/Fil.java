public class Fil extends Thread {
    private String nom;

    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Acaba el fil " + nom);
    }
}

