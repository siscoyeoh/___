package yl.swing.img;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import yl.swing.img.table.ActionPanelEditorRenderer;

/** 向table中插入按钮 */
public class TableInsertBtn {
	  
    
    JTable table = null;  
      
    DefaultTableModel model = null;  
      
      
    public JComponent makeUI() {  
        String[] columnNames = { "String", "Button" }; // When I increase the  
                                                        // number of columns it  
                                                        // hides another button  
        Object[][] data = { { "AAA", null }, { "BBB", null } };  
        model = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 8773716173515282323L;  
              
        };  
        table = new JTable(model);  
        table.setRowHeight(36);  
        ActionPanelEditorRenderer2 er = new ActionPanelEditorRenderer2();  
        TableColumn column = table.getColumnModel().getColumn(1);  
        column.setCellRenderer(er);  
        column.setCellEditor(er);  
          
        JPanel p = new JPanel(new BorderLayout());  
        p.add(new JScrollPane(table));  
        p.setPreferredSize(new Dimension(320, 200));  
        return p;  
    }  
  
    public static void main(String[] args) {  
//        EventQueue.invokeLater(new Runnable() {  
//            @Override  
//            public void run() {  
//                createAndShowGUI();  
//            }  
//        });  
    	// 下面的写法也可以
    	createAndShowGUI();
    }  
  
    public static void createAndShowGUI() {  
        JFrame f = new JFrame();  
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        f.getContentPane().add(new table().makeUI());  
        f.pack();  
        f.setLocationRelativeTo(null);  
        f.setVisible(true);  
    }  
  
      
      
    class ActionPanelEditorRenderer2 extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {  
		private static final long serialVersionUID = 1054570116491633534L;
		JPanel panel1 = new JPanel();  
        JPanel panel2 = new JPanel();  
  
        @Override  
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,  
                int row, int column) {  
            panel2.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());  
            panel2.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());  
              
              
              
        //  panel2.setBackground(table.getBackground());  
            return panel2;  
        }  
  
        @Override  
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {  
            panel2.setBackground(table.getSelectionBackground());  
            return panel2;  
        }  
  
        @Override  
        public Object getCellEditorValue() {  
            return null;  
        }  
    }  

}
