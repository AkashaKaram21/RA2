import java.util.Random;

public class Filosof extends Thread {

    private int numeroComensal;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private Object monitor;
    private Random random;

    public Filosof(int numeroComensal, Object monitor) {
        this.numeroComensal = numeroComensal;
        this.monitor = monitor;
        this.gana = 0;
        this.random = new Random();
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public int getNumeroComensal() {
        return numeroComensal;
    }

    public int getGana() {
        return gana;
    }

    private boolean agafarForquillaEsquerra() {
        if (forquillaEsquerra.getPropietari() == Forquilla.LLIURE) {
            forquillaEsquerra.setPropietari(numeroComensal);
            System.out.println("Filosof " + numeroComensal + " agafa forquilla esquerra " + forquillaEsquerra.getNumero());
            return true;
        }
        return false;
    }

    private boolean agafarForquillaDreta() {
        if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
            forquillaDreta.setPropietari(numeroComensal);
            System.out.println("Filosof " + numeroComensal + " agafa forquilla dreta " + forquillaDreta.getNumero());
            return true;
        }
        return false;
    }


    public void agafarForquilles() {
        boolean teEsquerra = false;
        boolean teDreta = false;

        while (!teEsquerra || !teDreta) {
            synchronized (monitor) {
                teEsquerra = agafarForquillaEsquerra();
                if (teEsquerra) {
                    teDreta = agafarForquillaDreta();
                }

                if (!teEsquerra || !teDreta) {
                    if (teEsquerra) {
                        forquillaEsquerra.setPropietari(Forquilla.LLIURE);
                        monitor.notifyAll();
                    }
                    teEsquerra = false;
                    teDreta = false;

                    long espera = 500 + (long) (random.nextDouble() * 500);
                    System.out.println("Filosof " + numeroComensal + " no pot menjar, espera " + espera + "ms");
                    try {
                        monitor.wait(espera);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public void dixarForquilles() {
        synchronized (monitor) {
            forquillaEsquerra.setPropietari(Forquilla.LLIURE);
            forquillaDreta.setPropietari(Forquilla.LLIURE);
            System.out.println("Filosof " + numeroComensal + " deixa les forquilles");
            monitor.notifyAll();
        }
    }

    public void menjar() {
        gana++;
        long temps = 1000 + (long) (random.nextDouble() * 1000);
        System.out.println("Filosof " + numeroComensal + " menja durant " + temps + "ms (vegada " + gana + ")");
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void pensar() {
        long temps = 1000 + (long) (random.nextDouble() * 1000);
        System.out.println("Filosof " + numeroComensal + " pensa durant " + temps + "ms");
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            agafarForquilles();
            menjar();
            dixarForquilles();
        }
    }
}