package src;

public class Barber extends Thread {
    private String nom;
    private Barberia barberia;

    public Barber(String nom, Barberia barberia) {
        this.nom = nom;
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Client client;

            synchronized (barberia.getCondBarber()) {
                client = barberia.seguentClient();
                if (client == null) {
                    System.out.println("Barber " + nom + " dormint");
                    try {
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue; 
                }
            }

            System.out.println("Li toca al client " + client.getNom());
            client.tallarSeElCabell();
            try {
                Thread.sleep(900 + (int)(Math.random() * 100)); // 0,9s + random 0,1s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}