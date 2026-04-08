import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    private int estatActual;
    private int ocupants;
    public static final int CAPACITAT_MAX = 3;

    private Semaphore capacitat;
    private ReentrantLock lockEstat;

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        // Según enunciado: new(CAPACITAT_MAX, true) y new ReentrantLock(true)
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }

    public void entraHome(String nom) {
        System.out.println(nom + " vol entrar al bany");
        boolean entrat = false;
        
        // Intenta infinitament
        while (!entrat) {
            lockEstat.lock();
            try {
                // Si l'estat actual és correcte
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    // Intenta bloquejar un tiquet del semàfor
                    if (capacitat.tryAcquire()) {
                        estatActual = BANY_AMB_HOMES;
                        ocupants++; // incrementa els ocupants
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        entrat = true;
                    }
                }
            } finally {
                // Finalment desbloqueja l'estat
                lockEstat.unlock();
            }
        }
    }

    public void entraDona(String nom) {
        System.out.println(nom + " vol entrar al bany");
        boolean entrat = false;
        
        while (!entrat) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        estatActual = BANY_AMB_DONES;
                        ocupants++;
                        // El enunciado usa "en el bany" para las mujeres
                        System.out.println("Dona entra en el bany. Ocupants: " + ocupants);
                        entrat = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
        }
    }

    public void surtHome() {
        lockEstat.lock();
        try {
            ocupants--; // disminueix ocupants
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
            
            // actualitza l'estat si correspon
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
            }
            capacitat.release(); // torna un tiquet
        } finally {
            // desbloqueja l'estat
            lockEstat.unlock();
        }
    }

    public void surtDona() {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
            }
            capacitat.release();
        } finally {
            lockEstat.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BanyUnisex bany = new BanyUnisex();

        Home[] homes = new Home[5];
        Dona[] dones = new Dona[5];

        for (int i = 0; i < 5; i++) {
            homes[i] = new Home("Home-" + i, bany);
            homes[i].start();
        }

        for (int i = 0; i < 5; i++) {
            dones[i] = new Dona("Dona-" + i, bany);
            dones[i].start();
        }

        for (int i = 0; i < 5; i++) {
            homes[i].join();
            dones[i].join();
        }

        System.out.println("El bany està buit");
    }
}