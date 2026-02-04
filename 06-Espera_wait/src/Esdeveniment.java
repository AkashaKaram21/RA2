import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Esdeveniment {
    private List<Assistent> assistents;
    private int placesDisponibles;
    private int placesMaxim;
    
    public Esdeveniment(int placesMaxim) {
        this.placesMaxim = placesMaxim;
        this.placesDisponibles = placesMaxim;
        this.assistents = new ArrayList<>();
    }
    
    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (placesDisponibles == 0) {
            wait();
        }
        
        if (assistents.contains(assistent)) {
            return;
        }
        
        assistents.add(assistent);
        placesDisponibles--;
        System.out.println(assistent.getNom() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
    }
    
    public synchronized void cancelaReserva(Assistent assistent) {
        if (assistents.contains(assistent)) {
            assistents.remove(assistent);
            placesDisponibles++;
            System.out.println(assistent.getNom() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            notifyAll();
        } else {
            System.out.println(assistent.getNom() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}