package clientside.view;

import clientside.view.elements.ApplicationMenu;
import clientside.view.elements.tableview.TableView;

import javax.swing.*;
import java.awt.*;

public class ClientSideView {
    private final JFrame mainWindow;
    private final TableView mainTable;

    private final ApplicationMenu appMenu;

    public ClientSideView() {
        mainWindow = new JFrame();
        appMenu = new ApplicationMenu();
        mainTable = new TableView();

        mainWindow.setJMenuBar(appMenu);
        mainWindow.add(mainTable, BorderLayout.CENTER);
    }

    public JFrame getFrame() { return mainWindow; }

    public ApplicationMenu getAppMenu() { return appMenu; }

    public TableView getMainTableView() { return mainTable; }
}
