public class Forquilla {
    Boolean enUs = false;
    int num_forquilles;

    public Forquilla (int num_forquilles) {
        this.num_forquilles = num_forquilles;
    }

    public Boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(Boolean enUs) {
        this.enUs = enUs;
    }

    public int getNum_forquilles() {
        return num_forquilles;
    }

    public void setNum_forquilles(int num_forquilles) {
        this.num_forquilles = num_forquilles;
    }

    public boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public void deixar() {
        enUs = false;
    }
}