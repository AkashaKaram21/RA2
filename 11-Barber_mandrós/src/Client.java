package src;

public class Client {
    private String nom;

    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public void tallarSeElCabell() {
        System.out.println("Tallant cabell a " + nom);
    }

    public String getNom() {
        return nom;
    }
}