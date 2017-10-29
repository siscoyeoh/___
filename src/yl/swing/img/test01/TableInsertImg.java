package yl.swing.img.test01;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/** 向table中插入img */
public class TableInsertImg extends JFrame {
	private static final long serialVersionUID = 5581690993097135439L;
	
	public static void main(String[]args) {
		
	}
	
	public TableInsertImg() {
		this.setTitle("JTable插入图片、复选框");
		this.setSize(600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		JTable table = new JTable();
		table.setAutoscrolls(true);
//		table.setModel(new MyTableColumnModel());
	}
	
	class MyTableColumnModel extends DefaultTableColumnModel {
		private static final long serialVersionUID = 8599754277719707560L;
		public int getColumn() {
			return 3;
		}
	}
	
}
