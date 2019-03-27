package Backend;

import java.util.EmptyStackException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Coada implements Runnable {
    private CopyOnWriteArrayList<Client> coada;
    private AtomicInteger waitingT;
    private int nume;
    private AtomicBoolean running = new AtomicBoolean(true);

    boolean isRunning() {
        return running.get();
    }

    public void interrupt() {
        running.set(false);
        Thread.currentThread().interrupt();
    }

    public Coada() {
        this.coada = new CopyOnWriteArrayList<Client>();
        this.waitingT = new AtomicInteger(0);
    }

    public CopyOnWriteArrayList<Client> getCoada() {
        return coada;
    }

    public AtomicInteger getWaitingT() {
        return this.waitingT;
    }

    public int getNrClienti() {
        return coada.size();
    }

    public int getNume() {
        return Integer.valueOf(nume);
    }


    public void addClient(Client newClient) {
        this.coada.add(newClient);
        this.waitingT.addAndGet(newClient.getServiceT());
        newClient.setCoada(nume);
    }

    public void removeClient(Client client) {
        for (Client c : coada) {
            if (c.getId() == client.getId()) {
                this.waitingT.set(this.waitingT.intValue() - client.getServiceT());
                coada.remove(client);
                for (Client client1 : coada) {
                    client1.setX(client1.getX() - 50);
                }
                break;
            }
        }
    }

    @Override
    public void run() {
        this.nume = Integer.parseInt(Thread.currentThread().getName());
        while (true) {
            synchronized (coada) {
                try {
                    coada.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Client c = new Client();
                try {
                    c = coada.remove(0);
                } catch (EmptyStackException e) {
                    e.printStackTrace();
                }
                int sleepT = c.getServiceT();
                try {
                    sleep(sleepT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitingT.addAndGet(-sleepT);
                if (coada.size() == 0) {
                    try {
                        coada.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Coada:" + coada +
                " timpul de asteptare total=" + waitingT +
                ", numele cozii='" + nume + '\'';
    }
}
