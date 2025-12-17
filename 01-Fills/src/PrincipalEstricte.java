public class PrincipalEstricte {
    public static void main(String[] args) {
        Fil filJuan = new Fil("Juan");
        Fil filPepe = new Fil("Pepe");
        
        System.out.println("Termina thread main");

        filJuan.start();
        filPepe.start();
    }
}
