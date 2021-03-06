package yl.swing;

import javax.swing.*;  

import java.awt.event.*;  
  
import javax.swing.tree.*;  
  
import java.awt.*;  
  
public class ShowContrlJtreeInClass extends JFrame implements ActionListener {  
	private static final long serialVersionUID = -4237567057449218701L;
	private JTree tree;  
    private JSplitPane sp;  
    private JPanel p2;  
    private JTextField textField;  
    private JButton b;  
    private DefaultTreeModel defaultTreeModel;  
    DefaultMutableTreeNode defaultMutableTreeNode;  
  
    ShowContrlJtreeInClass() {  
        textField = new JTextField(10);  
        b = new JButton("提交");  
        p2 = new JPanel();  
        p2.setBackground(Color.blue);  
        p2.add(textField);  
        p2.add(b);  
        defaultMutableTreeNode = new DefaultMutableTreeNode("吉大远程");  
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode("电子商务");  
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode("软件");  
        defaultMutableTreeNode.add(n1);  
        defaultMutableTreeNode.add(n2);  
        defaultTreeModel = new DefaultTreeModel(defaultMutableTreeNode);  
        tree = new JTree(defaultTreeModel);  
        JScrollPane js = new JScrollPane(tree);  
        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, js, p2);  
        this.add(sp);  
        this.setSize(400, 200);  
        this.setVisible(true);  
        this.setLocationRelativeTo(null);  
        b.addActionListener(this);  
    }  
  
    public void actionPerformed(ActionEvent arg0) {  
        // 创建一个新节点，以输入的内容为准。  
        String str = textField.getText();  
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(str);  
        defaultMutableTreeNode.add(n);// 将新建节点添加到根节点中。  
        defaultMutableTreeNode.remove(0);// 移除指定索引上的节点。  
  
        defaultTreeModel.reload();// 重新装载树控件的内容。  
        // root.getChildAt(0).toString();//获取指定索引上的子节点的内容。  
        // root.getChildCount();//获取子节点的数量。  
    }  
  
    public static void main(String[] arg) {  
        new ShowContrlJtreeInClass();  
    }  
  
} 