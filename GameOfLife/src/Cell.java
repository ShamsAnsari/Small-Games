import java.awt.*;

public class Cell {
    private boolean alive;

    public Cell() {
        alive = false;
    }

    public String toString(){
        return alive + "";
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
