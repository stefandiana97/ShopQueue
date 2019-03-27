package Backend;

import java.util.concurrent.CopyOnWriteArrayList;

public class Shortest_Time_Strategy implements Strategie {
    @Override
    public synchronized void addClient(CopyOnWriteArrayList<Coada> cozi, Client c) {
        int min = Integer.MAX_VALUE;
        for (Coada q : cozi) {
            if (q.getWaitingT().intValue() < min) {
                min = q.getWaitingT().intValue();
            }
        }
        for (Coada q : cozi) {
            if (q.getWaitingT().intValue() == min) {
                c.setExitT(q.getWaitingT().intValue());
                c.setY(q.getNume() * 53);
                c.setX(q.getNrClienti() * 50);
                q.addClient(c);
                notify();
                break;
            }

        }
    }
}
