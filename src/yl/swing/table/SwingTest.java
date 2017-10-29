package yl.swing.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SwingTest extends JFrame {
	JTable table;

	public SwingTest(String name) {
		super(name);
		/**********************************************
		 * ��JFrame����Ӳ˵��� ��ʼ��ά�ſƼ�it������
		 *******************************************/
		JMenuBar jmentBar = new JMenuBar();
		JMenu menu = new JMenu("�ļ�");
		JMenuItem it = new JMenuItem("����");
		jmentBar.add(menu);
		menu.add(it);
		setJMenuBar(jmentBar);
		/**********************************************
		 * ��JFrame����Ӳ˵��� ���� http://www.bjweixin.com/
		 *******************************************/
		// ����е�����
		String[] col = { "id", "�û���", "����", "����", "�Ա�", "����" };
		// ����table
		table = new JTable();
		// Ĭ�Ϲ����ά������ݵ�ʵ�� ��ά��it������
		DefaultTableModel mm = new DefaultTableModel(col, 0);
		// ���Դ����ݿ���ȡ��
		for (int i = 0; i < 30; i++) {
			String[] str_row = { "123", "123", "23", "321", "321" };
			mm.addRow(str_row);
		}
		// ��ʵ���ӵ����
		table.setModel(mm);
		// �������Ӽ�����
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// �޸ı��������
				System.out.println("�޸ı��������:\n" + e);
				printData(table);
			}
		});
		// �����������
		JScrollPane scrollPane = new JScrollPane(table);
		// �ӵ�pane��
		getContentPane().add(scrollPane);
		setSize(500, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void printData(JTable table) {
		// ��ȡ����������
		// int row = table.getRowCount();
		// int col = table.getColumnCount();
		// ��ȡѡ�е�����
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		// ��ȡ�������ݵ�ģʽ
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// ��ȡID
		String id = (String) model.getValueAt(row, 0);
		// ��ȡ����
		String a = model.getColumnName(col);
		System.out.println(model.getValueAt(row, col) + "" + a + "" + id);
		// �˴���������db���޸Ļ�ɾ������
	}

	public static void main(String[] args) {
		SwingTest s = new SwingTest("swing table");
	}
}
