public class Fil extends Thread {
    private String nom;

    // Constructor parametrizado para asignar el nombre al hilo
    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            
            // Simulación de carga de trabajo para forzar el entrelazado
            // sin usar Thread.sleep()
            for (int j = 0; j < 1000; j++) {
                // Bucle vacío para consumir ciclos de CPU
            }
        }
        System.out.println("Acaba el fil " + nom);
    }
}