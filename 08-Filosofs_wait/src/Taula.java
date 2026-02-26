public class Taula {

    private Filosof[] comensals;
    private Forquilla[] forquilles;
    private Object monitor;

    public Taula(int numFilosofs) {
        monitor = new Object();
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            comensals[i] = new Filosof(i, monitor);
        }

    
        for (int i = 0; i < numFilosofs; i++) {
            comensals[i].setForquillaEsquerra(forquilles[i]);
            comensals[i].setForquillaDreta(forquilles[(i + 1) % numFilosofs]);
        }
    }

    public void showTaula() {
        System.out.println("=== Estat de la Taula ===");
        for (Filosof f : comensals) {
            System.out.println("Filosof " + f.getNumeroComensal()
                    + " -> Forquilla esquerra: " + f.getForquillaEsquerra().getNumero()
                    + " | Forquilla dreta: " + f.getForquillaDreta().getNumero());
        }
        System.out.println("=========================");
    }

    public void cridarATaula() {
        for (Filosof f : comensals) {
            f.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(5);
        taula.showTaula();
        taula.cridarATaula();
    }
}