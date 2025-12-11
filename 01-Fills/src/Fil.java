public class Fil extends Thread {
    private String nombre;
    private int prioridad;
    
    // Constructor parametrizado
    public Fil(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    
    @Override
    public void run() {
        // Establecemos la prioridad del hilo
        this.setPriority(prioridad);
        
        // Bucle para simular trabajo
        for (int i = 1; i <= 1000; i++) {
            // Solo imprimimos algunos valores
            if (i <= 14) {
                System.out.println(nombre + " " + i);
            }
        }
    }
    
    public static void main(String[] args) {
        
        // Creamos dos hilos con la misma prioridad
        Fil hilo1 = new Fil("Pepe", Thread.NORM_PRIORITY);
        Fil hilo2 = new Fil("Joan", Thread.NORM_PRIORITY);
        
        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
        
        // El main sigue ejecutándose mientras los hilos corren
        System.out.println("Acaba thread main");
    }
}