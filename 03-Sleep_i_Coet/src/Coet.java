import java.util.Scanner;

public class Coet {
    private final Motor[] motors;

    public Coet() {

        motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void passaAPotencia(int p) {
        if (p >= 0 && p <= 10) {
            System.out.println("Passant a potència " + p);
            for (Motor m : motors) {
                m.setPotencia(p);
            }
        } else {
            System.out.println("Error de valor: La potència ha d'estar entre 0 i 10.");
        }
    }

    public void arranca() {
        for (Motor m : motors) {
            m.start();
        }
    }

    public static void main(String[] args) {
        Coet miCoet = new Coet();
        miCoet.arranca(); 

        Scanner scanner = new Scanner(System.in);
        int p = -1;

        while (p != 0) {
            if (scanner.hasNextInt()) {
                p = scanner.nextInt();
                miCoet.passaAPotencia(p);
            } else {
                scanner.next(); 
            }
        }
        
        System.out.println("Ordre de potència 0 rebuda. El coet s'aturarà.");
        scanner.close();
    }
}