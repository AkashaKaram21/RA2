public class Barri {
    public Estanc estanc;
    public Fumador fumadors[] = new Fumador[3];

    public Barri() {
        this.estanc = new Estanc();
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Barri barri = new Barri();
 
        for (int i = 0; i < 3; i++) {
            barri.fumadors[i].start();
        }
 
        barri.estanc.start();
 
        for (int i = 0; i < 3; i++) {
            barri.fumadors[i].join();
        }
 
        barri.estanc.tancarEstanc();
    }
}