public class Futbolista extends Thread {

    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;
    
    private int ngols;
    
    public Futbolista() {
        this.ngols = 0;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            double random = Math.random();
            
            if (random < PROBABILITAT) {
                ngols++;
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int getNgols() {
        return ngols;
    }
    
    public static void main(String[] args) {
        System.out.println("Inici dels xuts ---");
        
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista();
        }
        
        for (Futbolista jugador : jugadors) {
            jugador.start();
        }
        
        try {
            for (Futbolista jugador : jugadors) {
                jugador.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Fi dels xuts ---");
        System.out.println("--- Estadístiques ---");
        
        for (Futbolista jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.getNgols() + " gols");
        }
    }
}