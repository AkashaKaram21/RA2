public class Filosof extends Thread {

    private long iniciGana;
    private long fiGana;
    private long Gana;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private String nom;

    public Filosof(String nom) {
        this.nom = nom;
        this.iniciGana = System.currentTimeMillis(); 
        this.Gana = 0;
    }

    public String getNom() {
        return nom;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquilles(Forquilla esq, Forquilla dreta) {
        this.forquillaEsquerra = esq;
        this.forquillaDreta = dreta;
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void dixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }

    public void menjar() throws InterruptedException {
        agafarForquilles();
        System.out.println(nom + " té forquilles esq(" + forquillaEsquerra.getNum()
                + ") dreta(" + forquillaDreta.getNum() + ")");
        calcularGana();
        System.out.println(nom + " menja amb gana " + Gana);
        Thread.sleep((long)(1000 + Math.random() * 1000));
        System.out.println(nom + " ha acabat de menjar");
        resetGana();           
        dixarForquilles();
        System.out.println(nom + " deixa les forquilles");
    }

    public void pensar() throws InterruptedException {
        iniciGana = System.currentTimeMillis(); 
        System.out.println(nom + " pensant");
        Thread.sleep((long)(1000 + Math.random() * 1000));
    }

    public long calcularGana() {
        fiGana = System.currentTimeMillis();
        Gana = (fiGana - iniciGana) / 1000;
        return Gana;
    }

    public void resetGana() {
        iniciGana = System.currentTimeMillis();
        Gana = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                menjar();
                pensar();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}