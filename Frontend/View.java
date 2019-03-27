package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public class View {
    private JFrame frame = new JFrame("Simulare");
    private JPanel leftPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private JPanel upPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JTextField minNrClienti = new JTextField();
    private JTextField maxNrClienti = new JTextField();
    private JTextField minArrivalTime = new JTextField();
    private JTextField maxArrivalTime = new JTextField();
    private JTextField minServiceTime = new JTextField();
    private JTextField maxServiceTime = new JTextField();
    private JTextField nrCozi = new JTextField();
    private JTextField timeLimit = new JTextField();
    private JTextField miliSec = new JTextField();
    private JTextField mediaTimpului = new JTextField();
    private JTextField oraVarf = new JTextField();

    private JLabel nrClieniti = new JLabel("Numarul de clienti:");
    private JLabel nrMinClientiL = new JLabel("Min");
    private JLabel nrMaxClientiL = new JLabel("Max");
    private JLabel arrivalTime = new JLabel("Timpul de sosire:");
    private JLabel minArrivalTimeL = new JLabel("Min");
    private JLabel maxArrivalTimeL = new JLabel("Max");
    private JLabel serviceTime = new JLabel("Timpul de servire:");
    private JLabel minServiceTimeL = new JLabel("Min");
    private JLabel maxServiceTimeL = new JLabel("Max");
    private JLabel nrCoziL = new JLabel("Numarul de cozi:");
    private JLabel timeLimitL = new JLabel("Intervalul de simulare:");
    private JLabel miliSecL = new JLabel("Rapiditatea simularii(milisec):");
    private JLabel strategieL = new JLabel("Strategia de asezare a clientilor:");
    private JLabel mediaTimpuluiL = new JLabel("Media timpului de asteptare:");
    private JLabel oraVarfL = new JLabel("Ora de varf:");
    private JLabel activitate = new JLabel("Activitate: ");


    private JComboBox strategie = new JComboBox();

    private JTextArea textArea = new JTextArea(10, 5);
    private JScrollPane scrollPane = new JScrollPane
            (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JButton buton = new JButton("START");

    private PrintStream pS;
    //private FileInputStream fs;

    private int nrCozilor = 0;

    public View() {
        try {
            pS = new PrintStream(new OutputLogger(textArea));
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 20));
        leftPanel.add(nrClieniti);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel leftSubPanel1 = new JPanel();
        leftSubPanel1.setLayout(new BoxLayout(leftSubPanel1, BoxLayout.X_AXIS));
        leftSubPanel1.add(nrMinClientiL);
        setSizeLabel(nrMinClientiL);
        leftSubPanel1.add(minNrClienti);
        setSizeTextField(minNrClienti);
        leftSubPanel1.add(Box.createRigidArea(new Dimension(50, 0)));
        leftSubPanel1.add(nrMaxClientiL);
        setSizeLabel(nrMaxClientiL);
        leftSubPanel1.add(maxNrClienti);
        setSizeTextField(maxNrClienti);
        leftPanel.add(leftSubPanel1);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(arrivalTime);
        JPanel leftSubPanel2 = new JPanel();
        leftSubPanel2.setLayout(new BoxLayout(leftSubPanel2, BoxLayout.X_AXIS));
        leftSubPanel2.add(minArrivalTimeL);
        setSizeLabel(minArrivalTimeL);
        leftSubPanel2.add(minArrivalTime);
        setSizeTextField(minArrivalTime);
        leftSubPanel2.add(Box.createRigidArea(new Dimension(50, 0)));
        leftSubPanel2.add(maxArrivalTimeL);
        setSizeLabel(maxArrivalTimeL);
        leftSubPanel2.add(maxArrivalTime);
        setSizeTextField(maxArrivalTime);
        leftPanel.add(leftSubPanel2);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(serviceTime);
        JPanel leftSubPanel3 = new JPanel();
        leftSubPanel3.setLayout(new BoxLayout(leftSubPanel3, BoxLayout.X_AXIS));
        leftSubPanel3.add(minServiceTimeL);
        leftSubPanel3.add(minServiceTime);
        leftSubPanel3.add(Box.createRigidArea(new Dimension(50, 0)));
        leftSubPanel3.add(maxServiceTimeL);
        leftSubPanel3.add(maxServiceTime);
        setSizeTextField(minServiceTime);
        setSizeTextField(maxServiceTime);
        setSizeLabel(minServiceTimeL);
        setSizeLabel(maxServiceTimeL);
        leftPanel.add(leftSubPanel3);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel leftSubPanel4 = new JPanel();
        leftSubPanel4.setLayout(new BoxLayout(leftSubPanel4, BoxLayout.X_AXIS));
        leftSubPanel4.add(nrCoziL);
        leftSubPanel4.add(Box.createRigidArea(new Dimension(110, 0)));
        leftSubPanel4.add(nrCozi);
        leftPanel.add(leftSubPanel4);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel leftSubPanel5 = new JPanel();
        leftSubPanel5.setLayout(new BoxLayout(leftSubPanel5, BoxLayout.X_AXIS));
        leftSubPanel5.add(timeLimitL);
        leftSubPanel5.add(Box.createRigidArea(new Dimension(79, 0)));
        leftSubPanel5.add(timeLimit);
        leftPanel.add(leftSubPanel5);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel leftSubPanel6 = new JPanel();
        leftSubPanel6.setLayout(new BoxLayout(leftSubPanel6, BoxLayout.X_AXIS));
        leftSubPanel6.add(miliSecL);
        leftSubPanel6.add(Box.createRigidArea(new Dimension(35, 0)));
        leftSubPanel6.add(miliSec);
        leftPanel.add(leftSubPanel6);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(strategieL);
        strategie.addItem("SHORTEST_TIME_STRATEGY");
        strategie.addItem("SHORTEST_QUEUE_STRATEGY");
        strategie.addItem("RANDOM_STRATEGY");
        leftPanel.add(strategie);

        frame.add(leftPanel, BorderLayout.WEST);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(buton);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(mediaTimpuluiL);
        rightPanel.add(mediaTimpului);
        mediaTimpului.setEditable(false);
        rightPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(oraVarfL);
        rightPanel.add(oraVarf);
        oraVarf.setEditable(false);

        frame.add(rightPanel, BorderLayout.EAST);

        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.Y_AXIS));
        downPanel.add(activitate);
        downPanel.add(scrollPane);

        frame.add(downPanel, BorderLayout.SOUTH);

        pS = new PrintStream(new OutputLogger(textArea));
        System.setOut(pS);
        System.setErr(pS);

        minNrClienti.setText("20");
        maxNrClienti.setText("20");
        minArrivalTime.setText("1");
        maxArrivalTime.setText("1");
        minServiceTime.setText("1");
        maxServiceTime.setText("1");
        nrCozi.setText("8");
        timeLimit.setText("7");
        miliSec.setText("1000");

        frame.setVisible(true);
    }

    public void start(ActionListener start) {
        buton.addActionListener(start);
    }

    public void setSizeLabel(JLabel jLabel) {
        jLabel.setPreferredSize(new Dimension(30, 15));
    }

    public void setSizeTextField(JTextField jTextField) {
        jTextField.setPreferredSize(new Dimension(30, 15));
    }

    public String getMinNrClienti() {
        return minNrClienti.getText();
    }

    public String getMaxNrClienti() {
        return maxNrClienti.getText();
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public String getMinServiceTime() {
        return minServiceTime.getText();
    }

    public String getMaxServiceTime() {
        return maxServiceTime.getText();
    }

    public String getNrCozi() {
        return nrCozi.getText();
    }

    public String getTimeLimit() {
        return timeLimit.getText();
    }

    public String getMiliSec() {
        return miliSec.getText();
    }

    public String getStrategie() {
        return strategie.getSelectedItem().toString();
    }

    public void setMediaTimpului(String mediaTimpului) {
        this.mediaTimpului.setText(mediaTimpului);
    }

    public void setOraVarf(String oraVarf) {
        this.oraVarf.setText(oraVarf);
    }

    public void setNrCozilor(int nrCozilor) {
        this.nrCozilor = nrCozilor;
    }

    public void addClient(PaintClient c) {
        frame.add(c);
        frame.validate();
        frame.repaint();
    }

    public void addCasierie(PaintCasierie c) {
        frame.add(c);
        frame.revalidate();
        frame.repaint();
    }

    public void removeCasierie(PaintCasierie c) {

        frame.remove(c);
        frame.revalidate();
        frame.repaint();
    }

    public void removeClient(PaintClient c) {

        frame.remove(c);
        frame.revalidate();
        frame.repaint();
    }

    public void update() {
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) throws IOException {

        View v = new View();
        Listener aL = new Listener(v);
    }
}
