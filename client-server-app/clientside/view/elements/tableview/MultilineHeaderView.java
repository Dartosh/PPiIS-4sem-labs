package clientside.view.elements.tableview;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class MultilineHeaderView extends JTextArea implements TableCellRenderer {
    public MultilineHeaderView() {
        setEditable(false);
        setLineWrap(true);
        setOpaque(false);
        setFocusable(false);
        setWrapStyleWord(true);
        LookAndFeel.installBorder(this, "TableHeader.cellBorder");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int width = table.getColumnModel().getColumn(column).getWidth();
        setFont(table.getFont());
        setText((String)value);
        setSize(width, getPreferredSize().height);
        return this;
    }
}
