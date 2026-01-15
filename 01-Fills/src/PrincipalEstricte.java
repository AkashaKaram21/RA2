public class PrincipalEstricte {
    public static void main(String[] args) {
        Fil filJuan = new Fil("Juan",false);
        Fil filPepe = new Fil("Pepe",false);
        
        System.out.println("Termina thread main");

        filJuan.start();
        filPepe.start();
    }
}
