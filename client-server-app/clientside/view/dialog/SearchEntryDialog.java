package clientside.view.dialog;

import clientside.view.dialog.dialogelements.InputPanel;
import clientside.view.elements.tableview.TableView;

import javax.swing.*;
import java.awt.*;

public class SearchEntryDialog {
    private JFrame searchEntryWindow;
    private TableView matchesTable;

    private InputPanel inputData;

    public SearchEntryDialog() {
        searchEntryWindow = new JFrame();
        matchesTable = new TableView();

        inputData = new InputPanel();
        inputData.getActionButton().setText("Find");

        searchEntryWindow.add(inputData, BorderLayout.NORTH);
        searchEntryWindow.add(matchesTable, BorderLayout.CENTER);
    }

    public JFrame getFrame() { return searchEntryWindow;}

    public TableView getTableView() { return matchesTable; }

    public InputPanel getInputData() { return inputData; }
}
