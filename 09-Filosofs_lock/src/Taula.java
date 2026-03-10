public class Taula {

    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula(int n) {
        forquilles = new Forquilla[n];
        comensals  = new Filosof[n];

        for (int i = 0; i < n; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < n; i++) {
            comensals[i] = new Filosof("Fil" + i);
            comensals[i].setForquilles(forquilles[i], forquilles[(i + 1) % n]);
        }
    }

    public void showTaula() {
        for (Filosof f : comensals) {
            System.out.println("Comensal:" + f.getNom()
                    + " esq:" + f.getForquillaEsquerra().getNum()
                    + " dret:" + f.getForquillaDreta().getNum());
        }
        System.out.println("----------------------------");
    }

    public void cridarATaula() {
        for (Filosof f : comensals) {
            f.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}