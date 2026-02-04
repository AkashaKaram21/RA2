import java.util.Random;

public class Assistent extends Thread {
    private String nom;
    private Esdeveniment esdeveniment;
    private Random random;
    private boolean teReserva;
    
    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
        this.random = new Random();
        this.teReserva = false;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                if (random.nextBoolean()) {
                    if (!teReserva) {
                        esdeveniment.ferReserva(nom);
                        teReserva = true;
                    }
                } else {
                    if (teReserva) {
                        esdeveniment.cancelaReserva(nom);
                        teReserva = false;
                    } else {
                        esdeveniment.cancelaReserva(nom);
                    }
                }
                
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            // Se detiene el hilo
        }
    }
}