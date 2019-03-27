package Backend;

import java.util.concurrent.CopyOnWriteArrayList;

public class Planificator {
    private CopyOnWriteArrayList<Coada> sistemCozi;
    private int maxNrCozi;
    private Strategie strategie;

    public Planificator(int maxNrCozi) {
        this.maxNrCozi = maxNrCozi;
        sistemCozi = new CopyOnWriteArrayList<Coada>();
        for (int i = 0; i < maxNrCozi; i++) {
            Coada c = new Coada();
            Thread t = new Thread(c);
            t.setName(i + 1 + "");
            sistemCozi.add(c);
            t.start();
        }
    }

    public void schimbaStrategia(SelectieStrategie strategie) {
        if (strategie == SelectieStrategie.SHORTEST_QUEUE_STRATEGY) {
            this.strategie = new Shortest_Queue_Strategy();
        }
        if (strategie == SelectieStrategie.SHORTEST_TIME_STRATEGY) {
            this.strategie = new Shortest_Time_Strategy();
        }
        if (strategie == SelectieStrategie.RANDOM_STRATEGY) {
            this.strategie = new Random_Strategy();
        }
    }

    public synchronized void clientInStrategie(Client c) {
        this.strategie.addClient(sistemCozi, c);
    }

    public CopyOnWriteArrayList<Coada> getSistemCozi() {
        return sistemCozi;
    }

    public synchronized void stergeClient(Client c) {
        for (Coada q : sistemCozi) {
            q.removeClient(c);
        }
    }
}
