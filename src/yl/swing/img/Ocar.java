package yl.swing.img;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.Random;

public class Ocar extends JFrame {
	String[] columnNames = { "", "", "" };// ��Щ���Ǳ���ģ�������Ȼ���Ǵ�������
	Object[][] data = { { "", "", "" },// ��Щ���Ǳ���ģ�������Ȼ���Ǵ�������
			{ "", "", "" }, // ��Щ���Ǳ���ģ�������Ȼ���Ǵ�������
			{ "", "", "" } };// ��Щ���Ǳ���ģ�������Ȼ���Ǵ�������
	JTable table = null;

	public Ocar() {
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		table = new JTable(model) {
			private static final long serialVersionUID = 6538395051274140662L;

			public Class getColumnClass(int column) {// Ҫ��������table��Ҫ��д�������0��0����˼���Ǳ�ĸ��ӵ����Ͷ���0,0��һ����
				return getValueAt(0, 0).getClass();
			}
		};
		init();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == table) {
					init();
					int col = table.getSelectedColumn();
					int row = table.getSelectedRow();
					table.setValueAt((new ImageIcon("d:/image/find.png")), row,
							col);
					int random = new Random().nextInt(9);
					if (row != random / 3 || col != random % 3)
						table.setValueAt(new ImageIcon("d:/image/find.png"),
								random / 3, random % 3);
					else
						JOptionPane.showMessageDialog(null, "�㱻è����");
				}
			}
		});
		table.setRowHeight(70);
		table.setCellSelectionEnabled(false);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		getContentPane().add(table);
	}

	public void init() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				table.setValueAt(new ImageIcon("d:/image/find.png"), i, j);
	}

	public static void main(String[] args) {
		Ocar frame = new Ocar();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}