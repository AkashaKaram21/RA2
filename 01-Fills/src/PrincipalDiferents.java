public class PrincipalDiferents {
    public static void main(String[] args) {

        Fil pepe = new Fil("Pepe",true);
        Fil juan = new Fil("Juan",true);

        juan.setPriority(Thread.MAX_PRIORITY); 

        pepe.setPriority(Thread.MIN_PRIORITY); 

        pepe.start();
        
        juan.start();

        System.out.println("Acaba thread main");
    }
}