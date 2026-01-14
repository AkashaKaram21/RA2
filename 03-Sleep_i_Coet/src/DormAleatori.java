import java.util.Random;

public class DormAleatori extends Thread {

    private final long creationTime;
    private final Random random = new Random();

    public DormAleatori(String name) {
        super(name);
        this.creationTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {

                int intervalAleatori = random.nextInt(1000);
                
                long tempsTotal = System.currentTimeMillis() - creationTime;

                System.out.println(getName() + "(" + i + ") a dormir " + 
                                   intervalAleatori + "ms total " + 
                                   tempsTotal + "ms");

                Thread.sleep(intervalAleatori);
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrumpido: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();

        System.out.println("-- Fi de main ---------");
    }
}