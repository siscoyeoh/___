package yl.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SimpleTableDemo extends JPanel {
	private boolean DEBUG = true;
	  
	      public SimpleTableDemo() {
	          super(new BorderLayout());
	  
	          //������ͷ
	          String[] columnNames = { "First Name", "Last Name", "Sport",
	                  "# of Years", "Vegetarian" };
	  
	          //������ʾ����
	          Object[][] data = {
	                  { "Kathy", "Smith", "Snowboarding", new Integer(5),
	                          new Boolean(false) },
	                  { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
	                  { "Sue", "Black", "Knitting", new Integer(2),
	                          new Boolean(false) },
	                  { "Jane", "White", "Speed reading", new Integer(20),
	                          new Boolean(true) },
	                  { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
	          
	          /*
	           * JTable���ṩ��һ�����صĹ��췽��,��������Vector
	           * JTable(Vector rowData, Vector columnNames)
	           * 
	           */
	          
	          final JTable table = new JTable(data, columnNames);
	          
	          table.setBackground(Color.YELLOW);
	          
	          //table.setPreferredScrollableViewportSize(new Dimension(500, 0));
	          
	          if (DEBUG) {
	              table.addMouseListener(new MouseAdapter() {
	                  public void mouseClicked(MouseEvent e) {
	                      printDebugData(table);
	                  }
	              });
	          }
	  
	          // Create the scroll pane and add the table to it.
	          //��Ҳ�ǹٷ�����ʹ�õķ�ʽ�������ͷ������ʾ����Ҫ������ȡ��TableHeader�Լ��ֶ��������ʾ
	          JScrollPane scrollPane = new JScrollPane(table);
	  
	          add(scrollPane);
	              
	          
	          JPanel panel2 = new JPanel();
	          this.add(panel2,BorderLayout.SOUTH);
	          JButton btn1 = new JButton("������������ͼ");
	          JButton btn2 = new JButton("������������ͼ(Ĭ�ϲ����)");
	          panel2.add(btn1);
	          panel2.add(btn2);
	          
	          btn1.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  //���ñ�����������ͼ,��Ĭ�������,������Ĵ�СС����ͼ(����),��ᷢ�������������������ɫ,���Խ������yellowȥ�������Ƚ�
	                  table.setFillsViewportHeight(true);
	              }
	          });
	          
	          btn2.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  table.setFillsViewportHeight(false);
	              }
	          });
	          
	      }
	  
	      private void printDebugData(JTable table) {
	          int numRows = table.getRowCount();
	          int numCols = table.getColumnCount();
	          javax.swing.table.TableModel model = table.getModel();
	  
	          System.out.println("Value of data: ");
	          for (int i = 0; i < numRows; i++) {
	              System.out.print("    row " + i + ":");
	              for (int j = 0; j < numCols; j++) {
	                  System.out.print("  " + model.getValueAt(i, j));
	             }
	             System.out.println();
	         }
	         System.out.println("--------------------------");
	     }
	 
	     /**
	      * Create the GUI and show it. For thread safety, this method should be
	      * invoked from the event-dispatching thread.
	      */
	     private static void createAndShowGUI() {
	         // Create and set up the window.
	         JFrame frame = new JFrame("SimpleTableDemo");
	         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	         // Create and set up the content pane.
	         SimpleTableDemo newContentPane = new SimpleTableDemo();
	         newContentPane.setOpaque(true); // content panes must be opaque
	         frame.setContentPane(newContentPane);
	 
	         // Display the window.
	         //frame.pack();
	         frame.setSize(800, 600);
	         frame.setVisible(true);
	     }
	 
	     public static void main(String[] args) {
	         // Schedule a job for the event-dispatching thread:
	         // creating and showing this application's GUI.
	         javax.swing.SwingUtilities.invokeLater(new Runnable() {
	             public void run() {
	                 createAndShowGUI();
	             }
	         });
	   }
}
