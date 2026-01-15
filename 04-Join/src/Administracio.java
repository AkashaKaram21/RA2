public class Administracio {
    private final int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa;

    public Administracio() {
        this.poblacio_activa = new Treballador[num_poblacio_activa];
        
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador(
                "Ciutadà-" + i, 
                25000.0f, 
                20, 
                65
            );
        }
    }

    public void iniciarSimulacio() {
        for (Treballador t : poblacio_activa) {
            t.start();
        }
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        admin.iniciarSimulacio();
    }
}