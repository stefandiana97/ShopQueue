package Frontend;

import Backend.SelectieStrategie;
import Backend.Simulare;

import java.awt.event.ActionListener;

public class Listener {
    private View view;
    private Simulare simulare;
    private Simulare oldSimulare = null;

    public Listener(View view) {
        this.view = view;
        view.start(new Start());
    }

    class Start implements ActionListener {
        private int minTimeArrival;
        private int maxTimeArrival;
        private int minTimeServ;
        private int maxTimeServ;
        private int nrCozi;
        private int timeLimit;
        private int miliSec;
        private int nrMinClienti;
        private int nrMaxClienti;
        private PaintCasierie nrVechiCasieri = null;

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            try {
                minTimeArrival = Integer.parseInt(view.getMinArrivalTime());
                maxTimeArrival = Integer.parseInt(view.getMaxArrivalTime());
                minTimeServ = Integer.parseInt(view.getMinServiceTime());
                maxTimeServ = Integer.parseInt(view.getMaxServiceTime());
                nrCozi = Integer.parseInt(view.getNrCozi());
                view.setNrCozilor(nrCozi);
                timeLimit = Integer.parseInt(view.getTimeLimit());
                miliSec = Integer.parseInt(view.getMiliSec());
                nrMinClienti = Integer.parseInt(view.getMinNrClienti());
                nrMaxClienti = Integer.parseInt(view.getMaxNrClienti());
                SelectieStrategie selectieStrategie = null;
                if (view.getStrategie().equals("SHORTEST_TIME_STRATEGY")) {
                    selectieStrategie = SelectieStrategie.SHORTEST_TIME_STRATEGY;
                } else if (view.getStrategie().equals("SHORTEST_QUEUE_STRATEGY")) {
                    selectieStrategie = SelectieStrategie.SHORTEST_QUEUE_STRATEGY;
                } else if (view.getStrategie().equals("RANDOM_STRATEGY")) {
                    selectieStrategie = SelectieStrategie.RANDOM_STRATEGY;
                }
                if (oldSimulare != null) {
                    oldSimulare.interrupt();
                    removeClienti();
                }
                setCaserii();
                simulare = new Simulare(timeLimit, minTimeArrival, maxTimeArrival, minTimeServ, maxTimeServ, nrCozi, nrMinClienti, nrMaxClienti, selectieStrategie, miliSec, view);
                oldSimulare = simulare;
                Thread t = new Thread(simulare);
                t.start();
                view.update();
            } catch (Exception ex) {
                System.out.println("Date incorecte!");
                ex.fillInStackTrace();
            }
        }

        public void removeClienti() {

            // if(oldSimulare!=null && (oldSimulare.getListaClienti().size()!=0 && !oldSimulare.getRunning().get())) {
            for (PaintClient pc : oldSimulare.getListaClienti()) {
//                    	if(pc.getClient() == c){
                view.removeClient(pc);
//                      }
            }
            //}
        }

        public void setCaserii() {
            if (nrVechiCasieri != null) {
                view.removeCasierie(nrVechiCasieri);
            }
            PaintCasierie casierie = new PaintCasierie(nrCozi, 50, 50);
            nrVechiCasieri = casierie;
            view.addCasierie(nrVechiCasieri);
        }
    }
}
