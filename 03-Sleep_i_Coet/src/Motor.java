public class Motor extends Thread {
    private int actual = 0;
    private int objectiu = 0;
    private boolean canviPendent = false;

    public Motor(String nom) {
        super(nom);
    }

    public synchronized void ordenarPotencia(int p) {
        this.objectiu = p;
        this.canviPendent = true; 
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (canviPendent) {
                    String accio;
                    if (actual < objectiu) {
                        actual++;
                        accio = "Incre.";
                    } else if (actual > objectiu) {
                        actual--;
                        accio = "Decre.";
                    } else {
                        accio = "FerRes";
                        canviPendent = false; 
                    }

                    System.out.println(String.format("%s: %s Objectiu: %d Actual: %d", 
                        getName(), accio, objectiu, actual));

                    Thread.sleep(800 + (int)(Math.random() * 700));
                } else {
                    Thread.sleep(100); 
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}