import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    public List<Tabac> tabac;
    public List<Llumi> llumins;
    public List<Paper> paper;
    public Random rnd;
    public boolean tancar;

    public Estanc() {
        this.tabac = new ArrayList<>();
        this.llumins = new ArrayList<>();
        this.paper = new ArrayList<>();
        this.rnd = new Random();
        this.tancar = false;
    }

    public synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addLlumi() {
        llumins.add(new Llumi());
        System.out.println("Afegint Llumí");
        notifyAll();
    }

    public synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public void nouSubministrament() {
        int opcio = rnd.nextInt(3);
        if (opcio == 0) {
            addTabac();
        } else if (opcio == 1) {
            addLlumi();
        } else {
            addPaper();
        }
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (tabac.isEmpty() && !tancar) {
            wait();
        }
        if (!tabac.isEmpty()) {
            return tabac.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llumins.isEmpty() && !tancar) {
            wait();
        }
        if (!llumins.isEmpty()) {
            return llumins.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (paper.isEmpty() && !tancar) {
            wait();
        }
        if (!paper.isEmpty()) {
            return paper.remove(0);
        }
        return null;
    }

    public synchronized void tancarEstanc() {
        tancar = true;
        notifyAll();
        System.out.println("Estanc tancat");
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (!tancar) {
            nouSubministrament();
            try {
                long esperar = 500 + (long)(rnd.nextDouble() * 1000);
                Thread.sleep(esperar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}