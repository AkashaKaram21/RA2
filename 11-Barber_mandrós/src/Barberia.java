package src;

import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera;
    private int numCadire;
    private final Object condBarber = new Object();
    private static Barberia instancia;

    private Barberia(int numCadire) {
        salaEspera = new LinkedList<>();
        this.numCadire = numCadire;
    }

    public static Barberia getInstance(int numCadire) {
        if (instancia == null) {
            instancia = new Barberia(numCadire);
        }
        return instancia;
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public Client seguentClient() {
        if (salaEspera.isEmpty()) {
            System.out.println("Ningú en espera");
            return null;
        }
        return salaEspera.poll();
    }

    public void entrarClient(Client client) {
        synchronized (condBarber) {
            if (salaEspera.size() < numCadire) {
                salaEspera.offer(client);
                System.out.println("Client " + client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            entrarClient(new Client(i));
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }

        try { Thread.sleep(10000); } catch (InterruptedException e) {}

        for (int i = 11; i <= 20; i++) {
            entrarClient(new Client(i));
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        Barberia barberia = Barberia.getInstance(3);
        Barber barber = new Barber("Pepe", barberia);
        barber.start();
        barberia.start();
    }
}