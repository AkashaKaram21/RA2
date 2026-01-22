import java.util.Random;

class Soci extends Thread {
    private String nom;
    private Compte compte;
    private int aportacio;
    private int esperaMax;
    private Random random;
    
    public Soci(String nom, Compte compte, int aportacio, int esperaMax) {
        this.nom = nom;
        this.compte = compte;
        this.aportacio = aportacio;
        this.esperaMax = esperaMax;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        compte.ingres(aportacio);
        
        try {
            int tempsEspera = random.nextInt(esperaMax);
            Thread.sleep(tempsEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        compte.treu(10);
    }
}
