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
		 * 给JFrame中添加菜单条 开始（维信科技it教育）
		 *******************************************/
		JMenuBar jmentBar = new JMenuBar();
		JMenu menu = new JMenu("文件");
		JMenuItem it = new JMenuItem("保存");
		jmentBar.add(menu);
		menu.add(it);
		setJMenuBar(jmentBar);
		/**********************************************
		 * 给JFrame中添加菜单条 结束 http://www.bjweixin.com/
		 *******************************************/
		// 表格中的列名
		String[] col = { "id", "用户名", "密码", "年龄", "性别", "体重" };
		// 创建table
		table = new JTable();
		// 默认管理二维表格数据的实例 （维信it教育）
		DefaultTableModel mm = new DefaultTableModel(col, 0);
		// 可以从数据库中取出
		for (int i = 0; i < 30; i++) {
			String[] str_row = { "123", "123", "23", "321", "321" };
			mm.addRow(str_row);
		}
		// 把实例加到表格
		table.setModel(mm);
		// 给表格添加监听器
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 修改表格内数据
				System.out.println("修改表格内数据:\n" + e);
				printData(table);
			}
		});
		// 创建滚动面板
		JScrollPane scrollPane = new JScrollPane(table);
		// 加到pane中
		getContentPane().add(scrollPane);
		setSize(500, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void printData(JTable table) {
		// 获取到所有行数
		// int row = table.getRowCount();
		// int col = table.getColumnCount();
		// 获取选中的行数
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		// 获取管理数据的模式
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// 获取ID
		String id = (String) model.getValueAt(row, 0);
		// 获取列名
		String a = model.getColumnName(col);
		System.out.println(model.getValueAt(row, col) + "" + a + "" + id);
		// 此处可以连接db做修改或删除操作
	}

	public static void main(String[] args) {
		SwingTest s = new SwingTest("swing table");
	}
}
