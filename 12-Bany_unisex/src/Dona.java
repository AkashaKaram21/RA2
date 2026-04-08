class Dona extends Thread {
    private String nom;
    private BanyUnisex lavabo;

    public Dona(String nom, BanyUnisex lavabo) {
        this.nom = nom;
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        lavabo.entraDona(nom);
        try {
            utilitzaLavabo();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        lavabo.surtDona();
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    private void utilitzaLavabo() throws InterruptedException {
        // Temps d'ús: entre 2 i 3 segons (2000ms a 3000ms)
        long temps = 2000 + (long) (Math.random() * 1000);
        Thread.sleep(temps);
    }
}