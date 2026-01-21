import java.util.Random;

public class Treballador extends Thread {
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, float sou, int inici, int fi) {
        super(nom); // Establece el nombre del Thread
        this.sou_anual_brut = sou;
        this.edat_inici_treball = inici;
        this.edat_fi_treball = fi;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        this.cobrat += (sou_anual_brut / 12.0f);
    }

    public void pagaImpostos() {
        float impost = (sou_anual_brut / 12.0f) * 0.24f;
        this.cobrat -= impost;
    }

    @Override
    public void run() {
        for (edat_actual = 0; edat_actual < edat_fi_treball; edat_actual++) {
            if (edat_actual >= edat_inici_treball) {
                for (int mes = 0; mes < 12; mes++) {
                    cobra();
                    pagaImpostos();
                }
            }
        }
    }

    public float getCobrat() { return cobrat; }
    public int getEdat() { return edat_actual; }
}