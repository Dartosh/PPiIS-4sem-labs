package clientside.view.elements.tableview;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

public class TableView extends JPanel {
    private JTable applicationTable;

    private JButton firstPageButton;
    private JButton lastPageButton;
    private JButton nextPageButton;
    private JButton prevPageButton;

    private JPanel buttonPanel;

    public TableView() {
        setLayout(new BorderLayout());

        createUI();
        createTable();
    }

    private void createUI() {
        JPanel uiPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel = new JPanel(new GridLayout(1, 4));

        firstPageButton = new JButton("<<");
        firstPageButton.setBackground(Color.BLACK);
        firstPageButton.setForeground(Color.WHITE);
        lastPageButton = new JButton(">>");
        lastPageButton.setBackground(Color.BLACK);
        lastPageButton.setForeground(Color.WHITE);

        nextPageButton = new JButton(">");
        nextPageButton.setBackground(Color.BLACK);
        nextPageButton.setForeground(Color.WHITE);
        prevPageButton = new JButton("<");
        prevPageButton.setBackground(Color.BLACK);
        prevPageButton.setForeground(Color.WHITE);

        buttonPanel.add(firstPageButton);
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        buttonPanel.add(lastPageButton);

        uiPanel.add(buttonPanel);
        add(uiPanel, BorderLayout.NORTH);
    }

    private void createTable() {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Full name");
        columnNames.add("Course");
        columnNames.add("Group");
        columnNames.add("All works");
        columnNames.add("Completed works");
        columnNames.add("Programming language");

        JPanel tablePanel = new JPanel(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        applicationTable = new JTable(tableModel);
        applicationTable.setFont(new Font("Times Roman", Font.PLAIN, 15));
        applicationTable.getTableHeader().setBackground(Color.GRAY);

        applicationTable.getTableHeader().setPreferredSize(new Dimension(100, 50));
        applicationTable.setRowHeight(50);

        for (int columnIdx=0; columnIdx<columnNames.size(); columnIdx++)
            applicationTable.getColumnModel().getColumn(columnIdx).setHeaderRenderer(new MultilineHeaderView());

        tablePanel.add(applicationTable.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(applicationTable, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    public JTable getTable() { return applicationTable; }

    public JPanel getButtonPanel() { return buttonPanel; }

    public JButton getFirstPageButton() { return firstPageButton; }

    public JButton getLastPageButton() { return lastPageButton; }

    public JButton getNextPageButton() { return nextPageButton; }

    public JButton getPrevPageButton() { return prevPageButton; }
}
