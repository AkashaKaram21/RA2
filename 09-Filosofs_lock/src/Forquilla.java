import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {

    private int num;
    private ReentrantLock bloqueig;

    public Forquilla(int num) {
        this.num = num;
        this.bloqueig = new ReentrantLock(true); 
    }

    public int getNum() {
        return num;
    }

    public void agafar() {
        bloqueig.lock();
    }

    public void deixar() {
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock();
        }
    }
}