import java.util.Scanner;

public class Coet {
    public static void main(String[] args) {

        Motor[] motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor("Motor " + i);
            motors[i].start();
        }

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            if (sc.hasNextInt()) {
                int p = sc.nextInt();
                
                if (p < 0) break; 

                System.out.println("Passant a potència " + p);

                for (Motor m : motors) {
                    m.ordenarPotencia(p);
                }
            }
        }
        
        System.out.println("Apagant sistemes...");
        System.exit(0);
    }
}