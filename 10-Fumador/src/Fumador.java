import java.util.Random;

public class Fumador extends Thread {

    private int id;
    private Estanc estanc;

    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;

    private int fumades;
    private static final int MAX_FUMADES = 3;
    private Random random;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.fumades = 0;
        this.random = new Random();
    }

    public void compraTabac() {
        System.out.println("Fumador " + id + " comprant Tabac");
        tabac = estanc.venTabac();
    }

    public void compraPaper() {
        System.out.println("Fumador " + id + " comprant Paper");
        paper = estanc.venPaper();
    }

    public void compraLlumi() {
        System.out.println("Fumador " + id + " comprant Llumi");
        llumi = estanc.venLlumi();
    }

    public void fuma() throws InterruptedException {
        System.out.println("Fumador " + id + " fumant");
        Thread.sleep(500 + random.nextInt(501)); 
        fumades++;
        tabac = null;
        paper = null;
        llumi = null;
        System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
    }

    @Override
    public void run() {
        try {
            while (fumades < MAX_FUMADES) {
                synchronized (estanc) {
                    while (tabac == null) {
                        compraTabac();
                        if (tabac == null) estanc.wait();
                    }
                    while (paper == null) {
                        compraPaper();
                        if (paper == null) estanc.wait();
                    }
                    // Espera fins tenir llumi
                    while (llumi == null) {
                        compraLlumi();
                        if (llumi == null) estanc.wait();
                    }
                }
                fuma();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}