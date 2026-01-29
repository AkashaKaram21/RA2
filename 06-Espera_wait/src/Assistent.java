import java.util.Random;

public class Assistent extends Thread {
    private String nom;
    private Esdeveniment esdeveniment;
    private Random random;
    private boolean haFetReserva;

    public Assistent(String nom) {
        this.nom = nom;
        this.esdeveniment = new Esdeveniment(5);
        this.random = new Random();
        this.haFetReserva = false;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        try {
            if (random.nextBoolean()) {
                esdeveniment.ferReserva(this);
                haFetReserva = true;
            } else {
                if (haFetReserva) {
                    esdeveniment.cancelaReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
            }
            
            Thread.sleep(random.nextInt(1000));

        } catch (InterruptedException e) {
            System.out.println(nom + " ha estat interromput");
        }
    }
}