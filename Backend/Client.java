package Backend;

import java.awt.*;

import static java.awt.Color.white;

public class Client {
    private int id;
    private int arrivalT;
    private int serviceT;
    private int exitT;
    private int coadaNume;
    private int x;
    private int y;
    private int waitingT;
    private Color color;

    public Client(int id, int arrivalT, int serviceT, Color color) {
        this.id = id;
        this.arrivalT = arrivalT;
        this.serviceT = serviceT;
        this.exitT = arrivalT + serviceT;
        this.color = color;
    }

    public Client() {
        this(0, 0, 0, white);
    }

    public int getCoadaNume() {
        return coadaNume;
    }

    public void setCoada(int coada) {
        this.coadaNume = coada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    public int getServiceT() {
        return serviceT;
    }

    public int getExitT() {
        return exitT;
    }

    public void setExitT(int waitingTimeCoada) {
        this.exitT += waitingTimeCoada;
        this.waitingT = this.serviceT + waitingTimeCoada;
    }

    public int getWaitingT() {
        return waitingT;
    }

    public void updateWaitingT() {
        this.waitingT--;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Clientul cu " +
                "id=" + id +
                ", timpul de sosire=" + arrivalT +
                ", timpul de servire=" + serviceT +
                ", timpul de iesire=" + exitT +
                ", la coada='" + coadaNume + '\'' +
                "\n";
    }
}
