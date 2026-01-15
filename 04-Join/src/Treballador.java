import java.util.Random;

public class Treballador extends Thread {
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, float sou_anual_brut, int edat_inici, int edat_fi) {
        super(nom); 
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici;
        this.edat_fi_treball = edat_fi;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        this.cobrat += (this.sou_anual_brut / 12);
    }

    public void pagaImpostos() {
        float mensual = this.sou_anual_brut / 12;
        this.cobrat -= (mensual * 0.24f);
    }

    @Override
    public void run() {
        for (int i = 0; i <= edat_fi_treball; i++) {
            this.edat_actual = i;

            if (edat_actual >= edat_inici_treball && edat_actual < edat_fi_treball) {
                for (int mes = 1; mes <= 12; mes++) {
                    cobra();
                    pagaImpostos();
                }
            }
        }
        System.out.printf("%s -> edat: %d / total: %.2f%n", 
                this.getName(), this.edat_actual, this.cobrat);
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }
}