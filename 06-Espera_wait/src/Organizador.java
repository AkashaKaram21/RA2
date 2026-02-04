public class Organizador {
    public static void main(String[] args) throws InterruptedException {
        Esdeveniment esdeveniment = new Esdeveniment(5);
        
        Assistent[] assistents = new Assistent[10];
        for (int i = 0; i < 10; i++) {
            assistents[i] = new Assistent("Assistent-" + i, esdeveniment);
        }
        
        for (int i = 0; i < 10; i++) {
            assistents[i].start();
            Thread.sleep(100); // Pequeña pausa para simular el orden de la salida
        }
        
        // Dejamos que se ejecute un tiempo para ver la salida similar
        Thread.sleep(5000);
        
        // Detener todos los hilos
        for (int i = 0; i < 10; i++) {
            assistents[i].interrupt();
        }
        
        // Esperar a que terminen con join
        for (int i = 0; i < 10; i++) {
            assistents[i].join();
        }
    }
}