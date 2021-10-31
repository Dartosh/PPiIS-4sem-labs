package clientside.view.dialog;

import clientside.view.dialog.dialogelements.InputPanel;

import javax.swing.*;
import java.awt.*;

public class RemoveEntryDialog {
    private final JFrame deleteEntryWindow;
    private final InputPanel inputData;
    private final JLabel removalInfo;

    public RemoveEntryDialog() {
        deleteEntryWindow = new JFrame();
        removalInfo = new JLabel("");

        inputData = new InputPanel();
        inputData.getActionButton().setText("Delete");

        deleteEntryWindow.add(inputData, BorderLayout.NORTH);
        deleteEntryWindow.add(removalInfo, BorderLayout.CENTER);
    }

    public JFrame getFrame() { return deleteEntryWindow; }

    public InputPanel getInputData() { return inputData; }

    public JLabel getRemovalInfoLabel() { return removalInfo; }
}
