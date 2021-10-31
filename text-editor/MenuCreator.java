import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuCreator extends JMenuBar {

    private final static JMenuBar jMenuBar = new JMenuBar();

    private final static List<JMenu> jMenuList = new ArrayList<>();
    private final static List<JMenuItem> jMenuItemList = new ArrayList<>();

    public static JMenuBar getMainMenu(JTextPane textPane) {
        createJMenu(textPane);
        setJMenuSize();
        setJMenuItemSize();
        return jMenuBar;
    }

    public static void createJMenu(JTextPane textPane) {
        //MENU BAR items declaration
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        //JMenu help = new JMenu("Help");

        //Set MENU BAR items mnemonics
        file.setMnemonic('F');
        edit.setMnemonic('E');
        //help.setMnemonic('H');

        //Fulfill MENU BAR
        jMenuBar.add(file);
        jMenuBar.add(edit);
        //jMenuBar.add(help);

        //Fulfill MenuList
        jMenuList.add(file);
        jMenuList.add(edit);
        //jMenuList.add(help);

        //Fulfill MENU BAR items
        createJMenuItems(file, edit, textPane);
    }

    public static void createJMenuItems(JMenu file, JMenu edit, JTextPane textPane) {
        //FILE items declaration
        JMenuItem open = new JMenuItem("Open", 'O');
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openFile(open, textPane);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        JMenuItem save = new JMenuItem("Save", 'S');
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile(save, textPane);
            }
        });

        JMenuItem exit = new JMenuItem("Exit", 'E');
        exit.addActionListener(e -> System.exit(0));

        //Fulfill FILE menu item
        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);

        //EDIT items declaration
        JMenuItem copy = new JMenuItem("Copy", 'C');
        copy.addActionListener(e -> textPane.copy());

        JMenuItem cut = new JMenuItem("Cut", 't');
        cut.addActionListener(e -> textPane.cut());

        JMenuItem paste = new JMenuItem("Paste", 'P');
        paste.addActionListener(e -> textPane.paste());

        JMenuItem delete = new JMenuItem("Delete", 'D');
        delete.addActionListener(e -> textPane.replaceSelection(""));

        //Fulfill EDIT menu item
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(delete);

        //HELP items declaration
        JMenuItem commands = new JMenuItem("Commands", 'C');
        //Fulfill HELP menu item
        //help.add(commands);

        //Fulfill item list
        jMenuItemList.add(open);
        jMenuItemList.add(save);
        jMenuItemList.add(exit);
        jMenuItemList.add(copy);
        jMenuItemList.add(cut);
        jMenuItemList.add(paste);
        jMenuItemList.add(delete);
        jMenuItemList.add(commands);
    }

    public static void setJMenuSize() {
        for(JMenu temp : jMenuList)
            temp.setIconTextGap (30);
    }

    public static void setJMenuItemSize() {
        for(JMenuItem temp : jMenuItemList)
            temp.setIconTextGap (50);
    }

    private static void saveToFile(JMenuItem save, JTextPane textPane) {
        JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showSaveDialog(save);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                if (!file.getName().toLowerCase().endsWith(".xml")) {
                    file = new File(file.getParentFile(), file.getName() + ".xml");
                }
                try {
                    textPane.write(new OutputStreamWriter(new FileOutputStream(file)));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static void openFile(JMenuItem open, JTextPane textPane) throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showOpenDialog(open);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                if (!file.getName().toLowerCase().endsWith(".txt") || !file.getName().toLowerCase().endsWith(".xml")) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Scanner input = new Scanner(selectedFile);
                    //input.useDelimiter("\\A");
                    if (input.hasNext()) {
                        textPane.setText(input.next());
                    }
                }
                try {
                    textPane.write(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
