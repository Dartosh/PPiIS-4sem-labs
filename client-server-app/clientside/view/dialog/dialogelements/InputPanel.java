package clientside.view.dialog.dialogelements;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel{
    private final JTextField fullNameField;
    private final JTextField assignmentsLeftField;

    private final JTextField programmingLanguagesField;
    private final JTextField assignmentsAmountField;

    private final JButton actionButton;

    public InputPanel() {
        setLayout(new GridLayout(0, 1));
        Font mainFont = new Font("Times Roman", Font.PLAIN,17);

        fullNameField = new JTextField();
        fullNameField.setFont(mainFont);

        programmingLanguagesField = new JTextField();
        programmingLanguagesField.setFont(mainFont);

        assignmentsAmountField = new JTextField();
        assignmentsAmountField.setFont(mainFont);

        assignmentsLeftField = new JTextField();
        assignmentsLeftField.setFont(mainFont);

        JPanel fullNamePanel = new JPanel(new GridLayout(1, 2));
        fullNamePanel.add(new JLabel("Full name: "));
        fullNamePanel.add(fullNameField);
        add(fullNamePanel);

        JPanel programmingBoxLabel = new JPanel(new GridLayout(1, 2));
        programmingBoxLabel.add(new JLabel("Programming laguage: "));
        programmingBoxLabel.add(programmingLanguagesField);
        add(programmingBoxLabel);

        JPanel assignPanel = new JPanel(new GridLayout(1, 2));
        assignPanel.add(new JLabel("All works: "));
        assignPanel.add(assignmentsAmountField);
        add(assignPanel);

        JPanel assignmentsLeftPanel = new JPanel(new GridLayout(1, 2));
        assignmentsLeftPanel.add(new JLabel("Remaining works: "));
        assignmentsLeftPanel.add(assignmentsLeftField);
        add(assignmentsLeftPanel);

        actionButton = new JButton("");
        add(actionButton);
    }

    public JTextField getFullNameField() { return fullNameField; }

    public JTextField getAssignmentsLeftField() { return assignmentsLeftField; }

    public JTextField getProgrammingLanguagesField() { return programmingLanguagesField; }

    public JTextField getAssignmentsAmountField() { return assignmentsAmountField; }

    public JButton getActionButton() { return actionButton; }
}
