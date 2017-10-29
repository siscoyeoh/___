package yl.swing.table;

import javax.swing.table.AbstractTableModel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TableModel1 {
	public TableModel1() {
		JFrame frame = new JFrame();
		MyTable myTable = new MyTable();
		JTable table = new JTable(myTable);
		table.setRowHeight(36);
		table.setPreferredScrollableViewportSize(new Dimension(550, 300));
		JScrollPane scroll = new JScrollPane(table);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		frame.setTitle("JTable1");
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String args[]) {

		new TableModel1();
	}
}

class MyTable extends AbstractTableModel {
	private static final long serialVersionUID = 9007477009688926733L;
	Object[][] p = {
			{ "阿呆", new Integer(66), new Integer(32), new Integer(98),
					new Boolean(false), new Boolean(false) },
			{ "阿瓜", new Integer(85), new Integer(69), new Integer(154),
					new Boolean(true), new Boolean(false) } };
	String[] n = { "姓名", "语文", "数学", "总分", "及格", "作弊" };

	public int getColumnCount() {
		return n.length;
	}

	public int getRowCount() {
		return p.length;
	}

	public String getColumnName(int col) {
		return n[col];
	}

	public Object getValueAt(int row, int col) {
//		return p[row][col];
		return new ImageIcon("D:\\image\\LogincloseC.png");
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
