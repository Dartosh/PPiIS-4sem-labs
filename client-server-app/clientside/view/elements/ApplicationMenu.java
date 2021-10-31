package clientside.view.elements;

import javax.swing.*;

public class ApplicationMenu extends JMenuBar{
    JMenuItem saveItem;
    JMenuItem openItem;
    JMenuItem deleteFileItem;

    JMenuItem newEntryItem;
    JMenuItem searchItem;
    JMenuItem deleteItem;

    JMenuItem connectItem;


    public ApplicationMenu() {
        JMenu fileMenu = new JMenu("File");

        saveItem = new JMenuItem("Save");
        openItem = new JMenuItem("Load");
        deleteFileItem = new JMenuItem("Delete");

        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(deleteFileItem);

        JMenu toolsMenu = new JMenu("Tools");

        newEntryItem = new JMenuItem("Add");
        searchItem = new JMenuItem("Find");
        deleteItem = new JMenuItem("Delete");

        toolsMenu.add(newEntryItem);
        toolsMenu.add(searchItem);
        toolsMenu.add(deleteItem);

        JMenu serverMenu = new JMenu("Server");

        connectItem = new JMenuItem("Connect to the server");

        serverMenu.add(connectItem);

        add(fileMenu);
        add(toolsMenu);
        add(serverMenu);
    }

    public JMenuItem getSaveItem() { return saveItem; }

    public JMenuItem getOpenItem() { return openItem; }

    public JMenuItem getDeleteFileItem() { return deleteFileItem; }

    public JMenuItem getNewEntryItem() { return newEntryItem; }

    public JMenuItem getSearchItem() { return searchItem; }

    public JMenuItem getDeleteItem() { return deleteItem; }

    public JMenuItem getConnectItem() { return connectItem; }
}
