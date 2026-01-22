class Associacio {
    private int numSocis;
    private Soci[] socis;
    private Compte compte;
    
    public Associacio(int numSocis) {
        this.numSocis = numSocis;
        this.socis = new Soci[numSocis];
        this.compte = Compte.getInstancia();
    }
    
    public void iniciaCompteTempsSOcis() {
        int aportacio = 10;
        int esperaMax = 100;
        
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci("Soci-" + (i + 1), compte, aportacio, esperaMax);
        }
    }
    
    public void esperaPeriodeSocis() {
        for (int i = 0; i < numSocis; i++) {
            socis[i].start();
        }
        
        for (int i = 0; i < numSocis; i++) {
            try {
                socis[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void mostraBalancComptes() {
        System.out.println("Saldo final del compte: " + compte.getSaldo());
    }
    
    public static void main(String[] args) {
        int numSocis = 1000;
        
        Associacio associacio = new Associacio(numSocis);
        
        System.out.println("Iniciant simulació amb " + numSocis + " socis...");
        
        associacio.iniciaCompteTempsSOcis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}
