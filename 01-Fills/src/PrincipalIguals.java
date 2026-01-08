public class PrincipalIguals {
    public static void main(String[] args) {
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");

        fil1.setPriority(Thread.MAX_PRIORITY); 
        fil2.setPriority(Thread.MIN_PRIORITY); 

        fil1.start();
        fil2.start();

        System.out.println("Acaba thread main");
    }
}