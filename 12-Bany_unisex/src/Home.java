class Home extends Thread {
    private String nom;
    private BanyUnisex lavabo;

    public Home(String nom, BanyUnisex lavabo) {
        this.nom = nom;
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        lavabo.entraHome(nom);
        try {
            utilitzaLavabo();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        lavabo.surtHome();
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    private void utilitzaLavabo() throws InterruptedException {
        // Temps d'ús: entre 1 i 2 segons (1000ms a 2000ms)
        long temps = 1000 + (long) (Math.random() * 1000);
        Thread.sleep(temps);
    }
}