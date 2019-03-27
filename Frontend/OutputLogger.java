package Frontend;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class OutputLogger extends OutputStream {
    private JTextArea textArea;

    public OutputLogger(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.update(textArea.getGraphics());
    }
}
