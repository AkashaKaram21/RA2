public class PrincipalIguals {
    public static void main(String[] args) {

        Fil juan = new Fil("Juan",true);
        Fil pepe = new Fil("Pepe",true);

        juan.setPriority(Thread.MAX_PRIORITY);
        pepe.setPriority(Thread.MAX_PRIORITY);

        pepe.start();
        juan.start();

        System.out.println("Acaba thread main");
    }
}