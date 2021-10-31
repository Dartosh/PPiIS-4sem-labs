import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FrameCreator extends JFrame implements Serializable{
    //StyleContext sc = StyleContext.getDefaultStyleContext();
    private final JPanel topPanel;
    private final JPanel bottomPanel;
    private final JScrollPane scrollPane;
    private final JTextPane textPane;
    private final JPanel mainPanel;
    private final JPanel fontPanel;
    private final JPanel fileMenuPanel;
    private final JPanel editMenuPanel;
    private StyledDocument document;

    public FrameCreator() {
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        scrollPane = new JScrollPane();
        textPane = new JTextPane();
        mainPanel = new JPanel();
        fontPanel = new JPanel();
        fileMenuPanel = new JPanel();
        editMenuPanel = new JPanel();
        document = textPane.getStyledDocument();
    }

    public void setProperties() {
        setJMenuBar(MenuCreator.getMainMenu(textPane));
        setPreferredSize(new Dimension(1300, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTopPanel();
        setBottomPanel();
        setMainPanel();

        add(mainPanel);
        setVisible(true);
        revalidate();
        pack();
    }

    private void setMainPanel() {
        mainPanel.setBackground(Color.getHSBColor(225f, 0.16f, 0.20f));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
    }

    private void setTopPanel() {
        topPanel.setBackground(Color.getHSBColor(225f, 0.16f, 0.20f));
        topPanel.setLayout(new GridLayout());
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        setFontsPanel();
        setFileMenuPanel();
        setEditMenuPanel();
        topPanel.add(fontPanel);
        topPanel.add(fileMenuPanel);
        topPanel.add(editMenuPanel);
    }

    private void setBottomPanel() {
        bottomPanel.setBackground(Color.getHSBColor(222f, 0.16f, 0.24f));
        bottomPanel.setLayout(new GridLayout());
        textPane.setMargin(new Insets(20,20,20,20));
        scrollPane.setViewportView(textPane);
        bottomPanel.add(scrollPane);
    }

    private void setFontsPanel() {
        //Set panel with JComboBox with fonts
        fontPanel.setBackground(Color.getHSBColor(225f, 0.16f, 0.20f));
        String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JComboBox fonts = new JComboBox(fontList);
        Integer[] fontSizesList = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        JComboBox fontSizes = new JComboBox(fontSizesList);
        String[] fontStyleList = {"Bold", "Italic", "Regular"};
        JComboBox fontStyles = new JComboBox(fontStyleList);


        fonts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Посимвольное считывание атрибутов
                for(int i = 0; i < textPane.getSelectedText().length(); i++) {
                    //Присваиваем элементу выделенный текст (символ)
                    Element element = document.getCharacterElement(textPane.getSelectionStart() + i);
                    //Получаем атрибуты выделенного текста
                    AttributeSet as = element.getAttributes();
                    //Присваиваем сету атрибутов атрибуты выделенного текста
                    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
                    StyleConstants.setFontFamily(asNew, fontList[fonts.getSelectedIndex()]);

                    document.setCharacterAttributes(textPane.getSelectionStart() + i,
                            1, asNew, true);
                }
                    //Упрощенный вариант (без посимвольного считывания атрибутов):
                    /*
                    //Присваиваем элементу выделенный текст
                    Element element = document.getCharacterElement(textPane.getSelectionStart());
                    //Получаем атрибуты выделенного текста
                    AttributeSet as = element.getAttributes();
                    //Присваиваем сету атрибутов атрибуты выделенного текста
                    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
                    StyleConstants.setFontFamily(asNew, fontList[fonts.getSelectedIndex()]);

                    document.setCharacterAttributes(textPane.getSelectionStart(),
                            textPane.getSelectedText().length(), asNew, true);
                    */
            }
        });

        fontSizes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < textPane.getSelectedText().length(); i++) {
                    //Присваиваем элементу выделенный текст
                    Element element = document.getCharacterElement(textPane.getSelectionStart() + i);
                    //Получаем атрибуты выделенного текста
                    AttributeSet as = element.getAttributes();
                    //Присваиваем сету атрибутов атрибуты выделенного текста
                    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());

                    StyleConstants.setFontSize(asNew, fontSizesList[fontSizes.getSelectedIndex()]);
                    document.setCharacterAttributes(textPane.getSelectionStart() + i,
                            1, asNew, true);
                }
                //Упрощенный вариант (без посимвольного считывания атрибутов):
                /*
                //Присваиваем элементу выделенный текст
                Element element = document.getCharacterElement(textPane.getSelectionStart());
                //Получаем атрибуты выделенного текста
                AttributeSet as = element.getAttributes();
                //Присваиваем сету атрибутов атрибуты выделенного текста
                MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());

                StyleConstants.setFontSize(asNew, fontSizesList[fontSizes.getSelectedIndex()]);
                document.setCharacterAttributes(textPane.getSelectionStart(),
                        textPane.getSelectedText().length(), asNew, true);
                */
            }
        });

        fontStyles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < textPane.getSelectedText().length(); i++) {
                    //Присваиваем элементу выделенный текст
                    Element element = document.getCharacterElement(textPane.getSelectionStart() + i);
                    //Получаем атрибуты выделенного текста
                    AttributeSet as = element.getAttributes();
                    //Присваиваем сету атрибутов атрибуты выделенного текста
                    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());

                    switch (fontStyles.getSelectedIndex()) {
                        case 0:
                            StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
                            break;
                        case 1:
                            StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
                            break;
                        case 2:
                            StyleConstants.setItalic(asNew, false);
                            StyleConstants.setBold(asNew, false);
                            break;
                    }
                    document.setCharacterAttributes(textPane.getSelectionStart() + i,
                            1, asNew, true);
                }

                //Упрощенный вариант (без посимвольного считывания атрибутов):
                /*
                //Присваиваем элементу выделенный текст
                Element element = document.getCharacterElement(textPane.getSelectionStart());
                //Получаем атрибуты выделенного текста
                AttributeSet as = element.getAttributes();
                //Присваиваем сету атрибутов атрибуты выделенного текста
                MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());

                switch (fontStyles.getSelectedIndex()) {
                    case 0:
                        StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
                        break;
                    case 1:
                        StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
                        break;
                    case 2:
                        StyleConstants.setItalic(asNew, false);
                        StyleConstants.setBold(asNew, false);
                        break;
                }
                document.setCharacterAttributes(textPane.getSelectionStart(),
                        textPane.getSelectedText().length(), asNew, true);
                 */
            }
        });


        fontPanel.add(fonts);
        fontPanel.add(fontSizes);
        fontPanel.add(fontStyles);
    }

    private void setFileMenuPanel() {
        fileMenuPanel.setBackground(Color.getHSBColor(225f, 0.16f, 0.20f));

        JButton openButton = new JButton("Open");
        fileMenuPanel.add(openButton);
        openButton.addActionListener(e -> {
            try {
                openFile(openButton);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        JButton saveButton = new JButton("Save");
        fileMenuPanel.add(saveButton);

        saveButton.addActionListener(e -> saveToFile(saveButton));



        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        fileMenuPanel.add(exitButton);
        fontPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
    }

    private void setEditMenuPanel() {
        editMenuPanel.setBackground(Color.getHSBColor(225f, 0.16f, 0.20f));

        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(e -> textPane.copy());
        editMenuPanel.add(copyButton);

        JButton cutButton = new JButton("Cut");
        cutButton.addActionListener(e -> textPane.cut());
        editMenuPanel.add(cutButton);

        JButton pasteButton = new JButton("Paste");
        pasteButton.addActionListener(e -> textPane.paste());
        editMenuPanel.add(pasteButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> textPane.replaceSelection(""));
        editMenuPanel.add(deleteButton);
    }

    private void openFile(JButton open) throws FileNotFoundException {
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

    private void saveToFile(JButton save) {
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

}
