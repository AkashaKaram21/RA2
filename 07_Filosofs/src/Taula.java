public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula(int num_filosofs) {
        comensals = new Filosof[num_filosofs];
        forquilles = new Forquilla[num_filosofs];

        for (int i = 0; i < num_filosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int j = 0; j < num_filosofs; j++) {
                String nom = "fil" + j;
                Forquilla esquerra = forquilles[j];
                Forquilla dreta = forquilles[(j + 1) % num_filosofs];
                comensals[j] = new Filosof(nom, esquerra, dreta);
        }
    }
    public void showTaula() {
        System.out.println("\n=== Estado de la Taula ===");
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Comensal: fil" + i + " esq:" + i + " dret:" + 
                             ((i + 1) % comensals.length));
        }
        System.out.println("==========================\n");
    }
    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(5);
        taula.showTaula();
        taula.cridarATaula();
    }
}