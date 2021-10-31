package serverside.view.elements;

import javax.swing.*;
import java.awt.*;

public class LoggingArea extends JPanel{
    private final JTextArea logArea;

    public LoggingArea() {
        setLayout(new BorderLayout());


        setBackground(Color.BLACK);
        logArea = new JTextArea();
        logArea.setFont(new Font("Helvetica", Font.PLAIN,17));

        JScrollPane scroll = new JScrollPane(logArea);

        JLabel areaLabel = new JLabel(" History");
        areaLabel.setBackground(Color.BLACK);
        areaLabel.setForeground(Color.WHITE);
        areaLabel.setFont(new Font("Times Roman", Font.PLAIN,20));

        add(areaLabel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public JTextArea getLogArea() { return logArea; }
}
