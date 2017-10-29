package yl.swing.table;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class JTableTextAlignCenter extends JTable {
	private static final long serialVersionUID = -6285958850264370916L;
	private MyCellRenderer renderer;

	public JTableTextAlignCenter(MyCellRenderer renderer) {
		super(8, 6);
		this.renderer = renderer;
	}

	public TableCellRenderer getCellRenderer(int row, int column) {
		return renderer;
	}

	public static void main(String[] args) {
		JTableTextAlignCenter t = new JTableTextAlignCenter(
				new MyCellRenderer());
		JScrollPane pane1 = new JScrollPane(t);//
		JFrame frame = new JFrame("JTableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(pane1);
		frame.pack();
		frame.setVisible(true);

	}
}

class MyCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 8370089780156395582L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		setHorizontalAlignment(SwingConstants.CENTER);
		return this;
	}
}