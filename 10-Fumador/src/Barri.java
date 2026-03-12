public class Barri {

    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        estanc = new Estanc();

        fumadors = new Fumador[3];
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }

        for (Fumador f : fumadors) {
            f.start();
        }

        estanc.start();

        for (Fumador f : fumadors) {
            try {
                f.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        estanc.tancarEstanc();
    }

    public static void main(String[] args) {
        new Barri();
    }
}
