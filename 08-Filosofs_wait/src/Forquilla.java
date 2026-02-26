public class Forquilla {

    public static final int LLIURE = -1;

    private int numero;
    private int propietari;

    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }
}