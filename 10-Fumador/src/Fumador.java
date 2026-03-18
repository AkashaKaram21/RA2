import java.util.Random;

public class Fumador extends Thread {
    public Estanc estanc;
    public int id;
    public Tabac tabac;
    public Llumi llumi;
    public Paper paper;
    public int num_fumades;
    public Random rnd;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.rnd = new Random();
    }

    public void fuma() throws InterruptedException {
        if (tabac != null && llumi != null && paper != null) {
            System.out.println("Fumador " + id + " fumant");
            tabac = null;
            llumi = null;
            paper = null;
            num_fumades++;
            long esperar = 500 + (long)(rnd.nextDouble() * 500);
            Thread.sleep(esperar);
            System.out.println("Fumador " + id + " ha fumat " + num_fumades + " vegades");
        }
    }

    public void compraTabac() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Tabac");
        tabac = estanc.venTabac();
    }

    public void compraPaper() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Paper");
        paper = estanc.venPaper();
    }

    public void compraLlumi() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Llumí");
        llumi = estanc.venLlumi();
    }

    @Override
    public void run() {
        try {
            while (num_fumades < 3) {
                compraTabac();
                compraPaper();
                compraLlumi();
                fuma();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}