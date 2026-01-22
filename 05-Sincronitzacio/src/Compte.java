class Compte {
    private static Compte instancia = null;
    private int saldo;
    
    private Compte() {
        this.saldo = 0;
    }
    
    public static synchronized Compte getInstancia() {
        if (instancia == null) {
            instancia = new Compte();
        }
        return instancia;
    }
    
    public synchronized void ingres(int quantitat) {
        if (quantitat > 0) {
            saldo += quantitat;
        }
    }
    
    public synchronized void treu(int quantitat) {
        if (quantitat > 0 && saldo >= quantitat) {
            saldo -= quantitat;
        }
    }
    
    public int getSaldo() {
        return saldo;
    }
}
