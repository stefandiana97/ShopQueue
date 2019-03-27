package Backend;

import java.awt.*;
import java.util.Random;

public class ProcesareRandom {
    private int minTimeArrival;
    private int maxTimeArrival;
    private int minTimeServ;
    private int maxTimeServ;

    public ProcesareRandom(int minTimeArrival, int maxTimeArrival, int minTimeServ, int maxTimeServ) {
        this.minTimeArrival = minTimeArrival;
        this.maxTimeArrival = maxTimeArrival;
        this.minTimeServ = minTimeServ;
        this.maxTimeServ = maxTimeServ;
    }

    public int getMinTimeArrival() {
        return minTimeArrival;
    }

    public int getMaxTimeArrival() {
        return maxTimeArrival;
    }

    public int getMinTimeServ() {
        return minTimeServ;
    }

    public int getMaxTimeServ() {
        return maxTimeServ;
    }

    public int randomTime(int min, int max) {
        Random rand = new Random();
        //Va returna o valoare in intervalul [0,Max-Min), adaugand 1 asiguram ca si valoarea Max-Min va fi inclusa in interval
        //adaugam apoi Min ca sa scalam valoarea intre Min si Max
        return rand.nextInt((max - min) + 1) + min;
    }

    public Color randomColor() {
        Random random = new Random();
        int r = random.nextInt((255 - 153) + 1) + 153;
        int g = random.nextInt((153 - 60) + 1) + 60;
        int b = random.nextInt((158 - 153) + 1) + 153;
        Color color = new Color(r, g, b);
        return color;
    }
}
