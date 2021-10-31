package serverside.view.elements;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.*;

public class ControlPanel extends JPanel {
    JButton startButton;
    JButton stopButton;

    public ControlPanel() {
        setLayout(new GridLayout(1, 2));

        startButton = new JButton("Start server");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        stopButton = new JButton("Stop server");
        stopButton.setBackground(Color.BLACK);
        stopButton.setForeground(Color.WHITE);

        add(startButton);
        add(stopButton);
    }

    public JButton getStartButton() { return startButton; }

    public JButton getStopButton() { return stopButton; }
}
