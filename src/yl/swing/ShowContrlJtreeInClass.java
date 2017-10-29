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
        b = new JButton("�ύ");  
        p2 = new JPanel();  
        p2.setBackground(Color.blue);  
        p2.add(textField);  
        p2.add(b);  
        defaultMutableTreeNode = new DefaultMutableTreeNode("����Զ��");  
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode("��������");  
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode("���");  
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
        // ����һ���½ڵ㣬�����������Ϊ׼��  
        String str = textField.getText();  
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(str);  
        defaultMutableTreeNode.add(n);// ���½��ڵ���ӵ����ڵ��С�  
        defaultMutableTreeNode.remove(0);// �Ƴ�ָ�������ϵĽڵ㡣  
  
        defaultTreeModel.reload();// ����װ�����ؼ������ݡ�  
        // root.getChildAt(0).toString();//��ȡָ�������ϵ��ӽڵ�����ݡ�  
        // root.getChildCount();//��ȡ�ӽڵ��������  
    }  
  
    public static void main(String[] arg) {  
        new ShowContrlJtreeInClass();  
    }  
  
} 