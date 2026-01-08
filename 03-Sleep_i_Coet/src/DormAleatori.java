import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class DormAleatori extends Thread {
    private Instant tempsCreacio;
    private Random random;

    public DormAleatori(String nom) {
        super(nom);
        this.tempsCreacio = Instant.now();
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int intervalAleatori = random.nextInt(1000);
            
            long tempsTotal = Duration.between(tempsCreacio, Instant.now()).toMillis();
            
            System.out.printf("%s(%d) a dormir %3dms total %4dms%n", 
                getName(), i, intervalAleatori, tempsTotal);
            
            try {
                sleep(intervalAleatori);
            } catch (InterruptedException e) {
                System.out.println(getName() + " ha estat interromput.");
                return;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("-- F1 de main ---");
        
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        
        Instant inici = Instant.now();
        
        joan.start();
        pep.start();
        
        try {
            joan.join();
            pep.join();
        } catch (InterruptedException e) {
            System.out.println("El fil principal ha estat interromput.");
        }
        
        long tempsExecucio = Duration.between(inici, Instant.now()).toMillis();
        System.out.printf("--- Fi del main --- (temps total: %dms)%n", tempsExecucio);
    }
}