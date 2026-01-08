public class Fil extends Thread {
    private String nom;

    public Fil(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(this.nom + " " + i);

            try {
                Thread.sleep(1); 
            } catch (InterruptedException e) {
                System.out.println("Fil interromput");
            }
        }
        System.out.println("Acaba el fil " + this.nom);
    }
}