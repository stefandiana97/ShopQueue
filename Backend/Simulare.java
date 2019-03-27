package Backend;

import Frontend.PaintClient;
import Frontend.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class Simulare implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Simulare.class.getName());
    private int miliSec;
    private int timeLimit;
    private int minTimeArrival;
    private int maxTimeArrival;
    private int maxServT;
    private int minServT;
    private int nrCozi;
    private int nrMinClienti;
    private int nrMaxClienti;
    private int nrTotalClienti;
    private int oraVarf;
    private int mediaTimpuluiDeAsteptare = 0;
    private View view;
    private SelectieStrategie selectieStrategie;
    private int emptyQueue;
    private AtomicBoolean running;
    private Planificator planStrategie;
    private CopyOnWriteArrayList<Client> newClient;
    private ArrayList<PaintClient> listaClienti = new ArrayList<>();
    private int maxNrClienti = Integer.MIN_VALUE;

    public void interrupt() {
        running.set(false);
        Thread.currentThread().interrupt();
    }

    public AtomicBoolean getRunning() {
        return running;
    }

    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }

    public Simulare(int timeLimit, int minTimeArrival, int maxTimeArrival, int minServT, int maxServT, int nrCozi, int nrMinClienti, int nrMaxClienti, SelectieStrategie selectieStrategie, int miliSec, View view) {
        this.view = view;
        this.miliSec = miliSec;
        this.timeLimit = timeLimit;
        this.maxServT = maxServT;
        this.minServT = minServT;
        this.minTimeArrival = minTimeArrival;
        this.maxTimeArrival = maxTimeArrival;
        this.nrCozi = nrCozi;
        this.nrMaxClienti = nrMaxClienti;
        this.nrMinClienti = nrMinClienti;
        this.selectieStrategie = selectieStrategie;
        this.emptyQueue = 0;
        this.running = new AtomicBoolean(true);
        this.planStrategie = new Planificator(nrCozi);
        planStrategie.schimbaStrategia(this.selectieStrategie);
        this.newClient = new CopyOnWriteArrayList<Client>();
        genereazaClienti();
    }

    public void genereazaClienti() {
        ProcesareRandom procesareRandom = new ProcesareRandom(minTimeArrival, maxTimeArrival, minServT, maxServT);
        Random rand = new Random();
        nrTotalClienti = rand.nextInt((nrMaxClienti - nrMinClienti) + 1) + nrMinClienti;
        for (int i = 0; i < nrTotalClienti; i++) {
            int arrivalTime = procesareRandom.randomTime(procesareRandom.getMinTimeArrival(), procesareRandom.getMaxTimeArrival());
            int serviceTime = procesareRandom.randomTime(procesareRandom.getMinTimeServ(), procesareRandom.getMaxTimeServ());
            Color color = procesareRandom.randomColor();
            Client c = new Client(i, arrivalTime, serviceTime, color);
            newClient.add(c);
        }
        sort();
    }

    public void sort() {
        Collections.sort(this.newClient, new Comparator<Client>() {
            public int compare(Client o1, Client o2) {
                if (o1.getArrivalT() == o2.getArrivalT())
                    return (0);
                return o1.getArrivalT() > o2.getArrivalT() ? 1 : -1;
            }
        });
    }


    public void removeClient(Client c, int currentTime) {
        if (c.getExitT() == currentTime) {
            System.out.println("Clientul " + c.getId() + " a iesit la timpul " + currentTime + " din coada numarul " + c.getCoadaNume());
            planStrategie.stergeClient(c);
            for (PaintClient pc : listaClienti) {
//                    	if(pc.getClient() == c){
                view.removeClient(pc);
//                      }
            }
            listaClienti = new ArrayList<>();
            for (Coada coada : planStrategie.getSistemCozi()) {
                for (Client client : coada.getCoada()) {
                    PaintClient paintClient = new PaintClient(client);
                    listaClienti.add(paintClient);
                    view.addClient(paintClient);
                }
            }
            try {
                Thread.sleep(miliSec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient(Client c, int currentTime) {

        if (c.getArrivalT() == currentTime) {
            planStrategie.clientInStrategie(c);
            mediaTimpuluiDeAsteptare += c.getExitT() - c.getArrivalT();
            for (Coada coada : planStrategie.getSistemCozi()) {
                for (Client client : coada.getCoada()) {
                    try {
                        Thread.sleep(miliSec);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PaintClient paintClient = new PaintClient(client);
                    listaClienti.add(paintClient);
                    view.addClient(paintClient);
                }
                if (coada.getNrClienti() > maxNrClienti) {
                    maxNrClienti = coada.getNrClienti();
                    oraVarf = currentTime;
                }
            }
            System.out.println("Clientul " + c.getId() + " cu timpul de servicii: " + c.getServiceT() + " a intrat la timpul " + currentTime + " in coada numarul " + c.getCoadaNume());
//                    //LOGGER.info("Clientul "+c.getId()+" cu timpul de servicii: " + c.getServiceT() +" a intrat la timpul "+ currentTime+" in coada numarul "+c.getCoadaNume());
//                    for(Coada coada:planStrategie.getSistemCozi()) {
//                        //System.out.println(coada.getCoada());
//                        if (c.getCoadaNume() == coada.getNume()) {
//                            //LOGGER.info(coada.toString());
//                            System.out.println(coada.toString());
//                            System.out.println("Coada numarul "+coada.getNume()+" cu timpul de asteptare: " + coada.getWaitingT()+" si cu "+coada.getNrClienti()+" clienti");
//                            //LOGGER.info("Coada numarul "+k+" cu timpul de asteptare: " + coada.getWaitingT()+" si cu "+coada.getNrClienti()+" clienti");
//                            System.out.println("Nr de clienti: " + coada.getNrClienti());
//                        }
//                    }
        }
    }


    @Override
    public void run() {
        int currentTime = 0;
        while (running.get()) {
            while (currentTime < timeLimit) {
                System.out.println("Timpul curent este: " + currentTime);
                //LOGGER.info("Timpul curent este: "+currentTime);
                for (Client c : newClient) {
                    removeClient(c, currentTime);
                    addClient(c, currentTime);
                }
                currentTime++;
                for (Coada coada : planStrategie.getSistemCozi()) {
                    for (Client client : coada.getCoada()) {
                        client.updateWaitingT();
                    }
                }
                try {
                    Thread.sleep(miliSec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info("S-a incheiat simularea!");
            this.interrupt();
            view.setOraVarf(oraVarf + "");
            try {
                mediaTimpuluiDeAsteptare /= nrTotalClienti;
                view.setMediaTimpului(mediaTimpuluiDeAsteptare + "");
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    public ArrayList<PaintClient> getListaClienti() {
        return listaClienti;
    }
}