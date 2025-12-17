public class PrincipalDiferents {
    public static void main(String[] args) {
        Fil filJuan = new Fil("Juan");
        Fil filPepe = new Fil("Pepe");

        // Juan prioridad máxima (10)
        filJuan.setPriority(Thread.MAX_PRIORITY);
        // Pepe prioridad mínima (1)
        filPepe.setPriority(Thread.MIN_PRIORITY);

        filPepe.start();
        filJuan.start();

        System.out.println("Acaba thread main");
    }
}