package clientside.controller;

import clientside.model.PropertiesReader;
import clientside.view.ClientSideView;
import clientside.view.dialog.ArrayInteractionDialog;
import clientside.view.dialog.NewEntryDialog;
import clientside.view.dialog.RemoveEntryDialog;
import clientside.view.dialog.SearchEntryDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Properties;

public class MainClientController {
    Socket client;
    Properties connectProps;

    PrintStream out;
    ObjectInputStream in;

    Thread clientThread = null;
    private boolean isRunning = true;

    private final ClientSideView clientView;

    public MainClientController(ClientSideView view) {
        this.clientView = view;

        final int windowWidth = 1200;
        final int windowHeight = 700;

        clientView.getFrame().setSize(windowWidth, windowHeight);
        clientView.getFrame().setTitle("Client");
        clientView.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientView.getFrame().setVisible(true);

        initController();

        connectProps = PropertiesReader.readProperties("properties/config.properties");
    }

    private void initController() {
        clientView.getAppMenu().getSaveItem().addActionListener(e -> save());
        clientView.getAppMenu().getOpenItem().addActionListener(e -> load());
        clientView.getAppMenu().getDeleteFileItem().addActionListener(e -> delete());

        clientView.getAppMenu().getNewEntryItem().addActionListener(e -> initEntryDialog());
        clientView.getAppMenu().getSearchItem().addActionListener(e -> initSearchDialog());
        clientView.getAppMenu().getDeleteItem().addActionListener(e -> initRemoveDialog());

        clientView.getAppMenu().getConnectItem().addActionListener(e -> connect());

        clientView.getMainTableView().getFirstPageButton().addActionListener(e -> getFirstPage());
        clientView.getMainTableView().getLastPageButton().addActionListener(e -> getLastPage());
        clientView.getMainTableView().getNextPageButton().addActionListener(e -> getNextPage());
        clientView.getMainTableView().getPrevPageButton().addActionListener(e -> getPrevPage());
    }

    //Подключение к серверу
    private void connect() {
        //Создаётся поток
        if (clientThread == null) {
            //Переопределяется метод run()
            clientThread = new Thread() {
                @Override
                public void run() {
                    try {
                        //Создаём сокет клиента, в который мы помещаем адресс(IP), и порт
                        client = new Socket(connectProps.getProperty("address"), Integer.parseInt(connectProps.getProperty("port")));

                        //Поток для отправки на сервер (отправка команд string)
                        out = new PrintStream(client.getOutputStream());
                        //Поток для принимания студентов
                        in = new ObjectInputStream(client.getInputStream());

                        //Смена статуса
                        isRunning = true;
                        //Цкл ожидания ответа от сервера
                        while (isRunning) {
                            //Ожидание объекта от сервера
                            Object receivedArr = in.readObject();
                            if (receivedArr == null) continue;
                            updateTableView((ArrayList<Object[]>) (receivedArr));
                        }
                    }
                    catch (SocketException e) { clientThread = null; }
                    catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
                }
            };
            clientThread.start();
        }
    }

    private void initEntryDialog(){

        final int windowWidth = 500;
        final int windowHeight = 400;

        NewEntryDialog newEntryDialog = new NewEntryDialog();
        newEntryDialog.getFrame().setTitle("New entry");
        newEntryDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newEntryDialog.getFrame().setSize(windowWidth, windowHeight);
        newEntryDialog.getFrame().setVisible(true);

        newEntryDialog.getInputButton().addActionListener( e -> {
            boolean correctData = true;

            String fullName = newEntryDialog.getFullNameField().getText();
            String programmingLanguage = newEntryDialog.getProgrammingLanguageField().getText();

            String year = newEntryDialog.getYearField().getText();
            String groupId = newEntryDialog.getGroupIdField().getText();
            String assignmentsAmount = newEntryDialog.getAssignmentAmountField().getText();
            String passedAmount = newEntryDialog.getPassedAmountField().getText();

            try {
                Integer.parseInt(year);
                Integer.parseInt(groupId);
                Integer.parseInt(assignmentsAmount);
                Integer.parseInt(passedAmount);
            }
            catch (NumberFormatException numberFormatException) { correctData = false; }

            if(correctData && !fullName.isEmpty() && !programmingLanguage.isEmpty()) {
                //Отправка строки команды
                out.println(
                    "addRow| " +
                    fullName + " | " +
                    year + " | " +
                    groupId + " | " +
                    assignmentsAmount + " | " +
                    passedAmount + " | " +
                    programmingLanguage
                );

                newEntryDialog.getFrame().dispose();
            }
            else JOptionPane.showMessageDialog(null, "Wrong argument!");
        });
    }

    private void initSearchDialog() {
        final int windowWidth = 870;
        final int windowHeight = 700;

        SearchEntryDialog searchEntryDialog = new SearchEntryDialog();
        searchEntryDialog.getTableView().getButtonPanel().setVisible(false);
        searchEntryDialog.getFrame().setTitle("Search");
        searchEntryDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchEntryDialog.getFrame().setSize(windowWidth, windowHeight);
        searchEntryDialog.getFrame().setVisible(true);

        searchEntryDialog.getInputData().getActionButton().addActionListener(e -> {
            String fullName = searchEntryDialog.getInputData().getFullNameField().getText();
            String progLang = searchEntryDialog.getInputData().getProgrammingLanguagesField().getText();
            String assignmentsAmount = searchEntryDialog.getInputData().getAssignmentsAmountField().getText();
            String assignmentsLeft = searchEntryDialog.getInputData().getAssignmentsLeftField().getText();

            out.println("searchRow|" +
                    fullName + " | " +
                    progLang + " | " +
                    assignmentsAmount + " | " +
                    assignmentsLeft
            );

            try {
                //Костыль, создание нового клиента-сервер
                Thread.sleep(100);

                //Создаём сокет для нового клиента
                Socket searchClient = new Socket(connectProps.getProperty("address"), 9000);
                //
                ObjectInputStream outRead = new ObjectInputStream(searchClient.getInputStream());

                //То, что приходит от сервера (пакет)
                ArrayList<Object[]> foundArr = (ArrayList<Object[]>) outRead.readObject();

                DefaultTableModel tableViewModel = (DefaultTableModel) searchEntryDialog.getTableView().getTable().getModel();
                tableViewModel.setRowCount(0);

                foundArr.forEach(tableViewModel::addRow);

                searchClient.close();
            }
            catch (IOException | InterruptedException | ClassNotFoundException ex) { ex.printStackTrace(); }
        });
    }

    private void initRemoveDialog() {
        final int windowWidth = 850;
        final int windowHeight = 400;

        RemoveEntryDialog removeEntryDialog = new RemoveEntryDialog();
        removeEntryDialog.getFrame().setTitle("Remove");
        removeEntryDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeEntryDialog.getFrame().setSize(windowWidth, windowHeight);
        removeEntryDialog.getFrame().setVisible(true);

        removeEntryDialog.getInputData().getActionButton().addActionListener(e -> {
            String fullName = removeEntryDialog.getInputData().getFullNameField().getText();
            String progLang = removeEntryDialog.getInputData().getProgrammingLanguagesField().getText();
            String assignmentsAmount = removeEntryDialog.getInputData().getAssignmentsAmountField().getText();
            String assignmentsLeft = removeEntryDialog.getInputData().getAssignmentsLeftField().getText();

            out.println("removeRow|" +
                    fullName + " | " +
                    progLang + " | " +
                    assignmentsAmount + " | " +
                    assignmentsLeft
            );
            try {
                Thread.sleep(100);

                Socket removeClient = new Socket(connectProps.getProperty("address"), 8000);
                BufferedReader outRead = new BufferedReader(new InputStreamReader(removeClient.getInputStream()));

                int numRemoved = Integer.parseInt(outRead.readLine());
                //removeEntryDialog.getRemovalInfoLabel().setText("Deleted!");
                if (numRemoved == 0) removeEntryDialog.getRemovalInfoLabel().setText("Empty!");
                else removeEntryDialog.getRemovalInfoLabel().setText("Deleted!");


                removeClient.close();
            }
            catch (IOException | InterruptedException ex) { ex.printStackTrace(); }
        });
    }

    private void getNextPage() { if (out != null) out.println("nextPage"); }

    private void getPrevPage() { if (out != null) out.println("prevPage"); }

    private void getFirstPage() { if (out != null) out.println("firstPage"); }

    private void getLastPage() { if (out != null) out.println("lastPage"); }

    private void updateTableView(ArrayList<Object[]> newRows) {
        DefaultTableModel tableViewModel = (DefaultTableModel) clientView.getMainTableView().getTable().getModel();
        tableViewModel.setRowCount(0);
        newRows.forEach(tableViewModel::addRow);
    }

    private void load() {
        final int windowWidth = 400;
        final int windowHeight = 200;

        ArrayInteractionDialog interactionDialog = new ArrayInteractionDialog();
        interactionDialog.getFrame().setTitle("Load");
        interactionDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        interactionDialog.getFrame().setSize(windowWidth, windowHeight);
        interactionDialog.getFrame().setVisible(true);
        interactionDialog.getActionButton().setText("Load");

        interactionDialog.getActionButton().addActionListener(e -> {
            String fileName = interactionDialog.getArrayNameField().getText();
            if (out != null)  {
                out.println("load|"+ fileName + ".xml");
                interactionDialog.getFrame().dispose();
            };
        });
    }

    private void save() {
        final int windowWidth = 400;
        final int windowHeight = 200;

        ArrayInteractionDialog interactionDialog = new ArrayInteractionDialog();
        interactionDialog.getFrame().setTitle("Save");
        interactionDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        interactionDialog.getFrame().setSize(windowWidth, windowHeight);
        interactionDialog.getFrame().setVisible(true);
        interactionDialog.getActionButton().setText("Save");

        interactionDialog.getActionButton().addActionListener(e -> {
            String fileName = interactionDialog.getArrayNameField().getText();
            if (out != null)  {
                out.println("save|"+ fileName + ".xml");
                interactionDialog.getFrame().dispose();
            };
        });
    }

    private void delete() {
        final int windowWidth = 400;
        final int windowHeight = 200;

        ArrayInteractionDialog interactionDialog = new ArrayInteractionDialog();
        interactionDialog.getFrame().setTitle("Delete");
        interactionDialog.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        interactionDialog.getFrame().setSize(windowWidth, windowHeight);
        interactionDialog.getFrame().setVisible(true);
        interactionDialog.getActionButton().setText("Delete");

        interactionDialog.getActionButton().addActionListener(e -> {
            String fileName = interactionDialog.getArrayNameField().getText();
            if (out != null)  {
                out.println("delete|"+ fileName + ".xml");
                interactionDialog.getFrame().dispose();
            };
        });
    }
}
