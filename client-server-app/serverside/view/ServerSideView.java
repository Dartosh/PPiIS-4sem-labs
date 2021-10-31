package serverside.view;

import serverside.view.elements.ControlPanel;
import serverside.view.elements.LoggingArea;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;

public class ServerSideView {
    private final JFrame serverWindow;
    private final ControlPanel serverControlPanel;
    private final LoggingArea logArea;

    public ServerSideView() {
        serverWindow = new JFrame();
        serverControlPanel = new ControlPanel();
        logArea = new LoggingArea();

        serverWindow.add(logArea, BorderLayout.CENTER);
        serverWindow.add(serverControlPanel, BorderLayout.NORTH);
    }

    public JFrame getWindow() { return serverWindow; }

    public ControlPanel getControlPanel() { return serverControlPanel; }

    public LoggingArea getLoggingArea() { return logArea; }
}
