package yl.swing.table;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class JTableTextAlignCenter2 extends JTable {
	private static final long serialVersionUID = -6285958850264370916L;
	private MyCellRenderer2 renderer;

	public JTableTextAlignCenter2(MyCellRenderer2 renderer) {
		super(8, 6);
		this.renderer = renderer;
	}

	public TableCellRenderer getCellRenderer(int row, int column) {
		return renderer;
	}

	public static void main(String[] args) {
		JTableTextAlignCenter2 t = new JTableTextAlignCenter2(
				new MyCellRenderer2());
		JScrollPane pane1 = new JScrollPane(t);//
		JFrame frame = new JFrame("JTableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(pane1);
		frame.pack();
		frame.setVisible(true);

	}
}

class MyCellRenderer2 extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 8370089780156395582L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		setHorizontalAlignment(SwingConstants.CENTER);
		return this;
	}
	
}