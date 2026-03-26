import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {

    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    private int estatActual = BANY_BUIT;
    private int ocupants = 0;
    private final int CAPACITAT_MAX = 3;

    private Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);
    private ReentrantLock lockEstat = new ReentrantLock(true);

    public void entraHome() {
        lockEstat.lock();
        try {
            while () {
                lockEstat.unlock();
                Thread.yield();
                lockEstat.lock();
            }
            if (capacitat.tryAcquire()) {
                ocupants++;
                estatActual = BANY_AMB_HOMES;
                System.out.println("Home entra al bany. Ocupants: " + ocupants);
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void entraDona() {
        lockEstat.lock();
        try {
            while () {
                lockEstat.unlock();
                Thread.yield();
                lockEstat.lock();
            }
            if (capacitat.tryAcquire()) {
                ocupants++;
                estatActual = ;
                System.out.println("Dona entra al bany. Ocupants: " + ocupants);
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtHome() {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            if (ocupants == 0) {
                estatActual = ;
            }
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona() {
        lockEstat.lock();
        try {

        } finally {
            lockEstat.unlock();
        }
    }
}