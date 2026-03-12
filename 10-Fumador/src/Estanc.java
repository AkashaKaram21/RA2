import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {

    private List<Tabac> llistaTabac;
    private List<Paper>  llistaPaper;
    private List<Llumi>  llistaLlumi;

    private boolean obert; 
    private Random random;

    public Estanc() {
        llistaTabac = new ArrayList<>();
        llistaPaper  = new ArrayList<>();
        llistaLlumi  = new ArrayList<>();
        obert = true;
        random = new Random();
        System.out.println("Estanc obert");
    }

    private synchronized void addTabac() {
        llistaTabac.add(new Tabac());
        System.out.println("Afegint tabac");
    }

    private synchronized void addPaper() {
        llistaPaper.add(new Paper());
        System.out.println("Afegint Paper");
    }

    private synchronized void addLlumi() {
        llistaLlumi.add(new Llumi());
        System.out.println("Afegint Llumi");
    }

    public synchronized void nouSubministrament() {
        int tipus = random.nextInt(3); 
        if (tipus == 0) addTabac();
        else if (tipus == 1) addPaper();
        else addLlumi();

        notifyAll(); 
    }

    public synchronized Tabac venTabac() {
        if (llistaTabac.isEmpty()) return null;
        return llistaTabac.remove(0);
    }

    public synchronized Paper venPaper() {
        if (llistaPaper.isEmpty()) return null;
        return llistaPaper.remove(0);
    }

    public synchronized Llumi venLlumi() {
        if (llistaLlumi.isEmpty()) return null;
        return llistaLlumi.remove(0);
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll(); 
    }

    @Override
    public void run() {
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(1001));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}