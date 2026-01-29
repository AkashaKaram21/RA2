import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private static List<Assistent> llistaReserves = new ArrayList<>();
    
    private int placesMaxim;
    private int placesDisponibles;

    public Esdeveniment(int placesMaxim) {
        this.placesMaxim = placesMaxim;
        this.placesDisponibles = placesMaxim;
    }

    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (placesDisponibles <= 0) {
            wait();
        }
        
        synchronized(llistaReserves) {
            llistaReserves.add(assistent);
        }
        
        placesDisponibles--;
        System.out.println(assistent.getNom() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        boolean trobat = false;
        
        synchronized(llistaReserves) {
            trobat = llistaReserves.contains(assistent);
            if (trobat) {
                llistaReserves.remove(assistent);
            }
        }
        
        if (trobat) {
            placesDisponibles++;
            System.out.println(assistent.getNom() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            
            notifyAll();
        } else {
            System.out.println(assistent.getNom() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }

    public synchronized int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public synchronized int getPlacesOcupades() {
        return placesMaxim - placesDisponibles;
    }
    
    public static synchronized int getNumReservesTotal() {
        return llistaReserves.size();
    }
}