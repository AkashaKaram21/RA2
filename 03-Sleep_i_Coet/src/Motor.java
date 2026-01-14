import java.util.Random;

public class Motor extends Thread {
    private final int id;
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private boolean actiu = true;
    private final Random random = new Random();

    public Motor(int id) {
        this.id = id;
    }

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
    }

    @Override
    public void run() {
        try {
            while (actiu) {
                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    System.out.println("Motor " + id + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    Thread.sleep(1000 + random.nextInt(1001)); 
                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    System.out.println("Motor " + id + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    Thread.sleep(1000 + random.nextInt(1001)); 
                } else {
                    if (potenciaActual != 0 || potenciaObjectiu != 0) {
                    
                    }
                    
                    if (potenciaObjectiu == 0 && potenciaActual == 0) {
                        System.out.println("Motor " + id + ": FerRes Objectiu: 0 Actual: 0 (Aturat)");
                        actiu = false;
                    }
                    
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}