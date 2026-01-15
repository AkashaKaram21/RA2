public class Fil extends Thread {
    private String nom;
    private boolean usaBucle; 

    public Fil(String nom, boolean usaBucle) {
        this.nom = nom;
        this.usaBucle = usaBucle;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            
            if (this.usaBucle) {
                for (int j = 0; j < 1000; j++) {
                }
            } else {
                try {
                    Thread.sleep(1); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Acaba el fil " + nom);
    }
}