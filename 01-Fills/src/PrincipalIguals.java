public class PrincipalIguals {
    public static void main(String[] args) {
        // Crear las dos instancias solicitadas
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        // Configurar prioridades (se pide MAX o MIN para forzar al planificador)
        // En este caso, ponemos ambos a MAX_PRIORITY para que compitan equitativamente
        juan.setPriority(Thread.MAX_PRIORITY);
        pepe.setPriority(Thread.MAX_PRIORITY);

        // Iniciar los hilos
        pepe.start();
        juan.start();

        // El hilo principal termina de dar las órdenes y finaliza
        System.out.println("Acaba thread main");
    }
}