import java.util.Random;

class Assistent extends Thread {
    private String nom;
    private Esdeveniment esdeveniment;
    private Random random;
    
    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
        this.random = new Random();
    }
    
    public String getNom() {
        return nom;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                if (random.nextBoolean()) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
                
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            System.out.println(nom + " ha estat interromput.");
        }
    }
}
