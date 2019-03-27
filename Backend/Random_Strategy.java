package Backend;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Random_Strategy implements Strategie {
    @Override
    public synchronized void addClient(CopyOnWriteArrayList<Coada> cozi, Client c) {
        Random rand = new Random();
        int nrCoada = rand.nextInt((cozi.size() - 0) + 1) + 0;
        int k = 1;
        for (Coada q : cozi) {
            if (k == nrCoada) {
                c.setExitT(q.getWaitingT().intValue());
                c.setY(q.getNume() * 53);
                c.setX(q.getNrClienti() * 50);
                q.addClient(c);
                notify();
                break;
            }
            k++;
        }
    }


}
