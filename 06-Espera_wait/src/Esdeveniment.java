import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private List<String> assistents;
    private int placesMaximes;
    private int placesDisponibles;
    
    public Esdeveniment(int placesMaximes) {
        this.placesMaximes = placesMaximes;
        this.placesDisponibles = placesMaximes;
        this.assistents = new ArrayList<>();
    }
    
    public synchronized void ferReserva(String nomAssistent) throws InterruptedException {
        while (placesDisponibles <= 0) {
            wait();
        }
        
        if (!assistents.contains(nomAssistent)) {
            assistents.add(nomAssistent);
            placesDisponibles--;
            System.out.println(nomAssistent + " ha fet una reserva. Places disponibles: " + placesDisponibles);
        }
    }
    
    public synchronized void cancelaReserva(String nomAssistent) {
        if (assistents.contains(nomAssistent)) {
            assistents.remove(nomAssistent);
            placesDisponibles++;
            System.out.println(nomAssistent + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            notifyAll();
        } else {
            System.out.println(nomAssistent + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}