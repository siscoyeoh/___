package yl.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;

public class Notepad extends JFrame{
	private JTextArea editor;
    private Container c;
    private Font f=new Font("sanserif",Font.PLAIN,12);
    //�˵�����Ա��������
    private JMenuBar mb;        //�˵���
    private JMenu fileMenu;     //�ļ��˵�
    private JMenu editMenu;     //�༭�˵�
    private JMenu formatMenu;   //��ʽ�˵�
    private JMenuItem fileMenuOpen,fileMenuSave,fileMenuExit;   //�ļ��˵��Ĳ˵���
    private JMenuItem editMenuCopy,editMenuCut,editMenuPaste;   //�༭�˵��Ĳ˵���
    private JMenu formatSet;//��ʽ�˵���һ���˵�
    private JMenuItem lineWrap,cancleLineWrap,wrapStyleWord,cancleWrapStyleWord;//��ʽ�˵��ĵڶ����˵�
  
    //��������Ա��������
    private JToolBar toolBar;   //���߰�
    private JButton b1,b2,b3,b4,b5; //����5����ť���ֱ�Ϊ�����򿪡��������桱�������ơ��������С�����ճ����
      
    //����ʽ�˵���������
    private JPopupMenu pm;
    private JMenuItem item1,item2,item3,item4,item5;
  
  
  
    public Notepad()
    {
        super("�򵥼��±�");
        setSize(400,300);
      
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){ System.err.println("���ܱ�������۵�ԭ��:"+e);}
          
  
  
        c=getContentPane();         //����һ���������
        editor = new JTextArea();   //����һ���ı���
        c.add(new JScrollPane(editor)); //���ù�����������ӵ��������
  
        //�˵�����ʵ��
        //�ļ��˵���ʵ��
        mb = new JMenuBar();    //�����˵���
        fileMenu = new JMenu("�ļ�(F)");//�����˵�
        fileMenuOpen = new JMenuItem("��(O)...Ctrl+O");
        fileMenuSave = new JMenuItem("����(S)...Ctrl+S");
        fileMenuExit = new JMenuItem("�˳�");
        JMHandler JM=new JMHandler();       //����������
        fileMenuOpen.addActionListener(JM); //ע�������
        fileMenuSave.addActionListener(JM);
        fileMenuExit.addActionListener(JM);
        fileMenu.add(fileMenuOpen);
        fileMenu.add(fileMenuSave);
        fileMenu.addSeparator();    //��ӷָ���
        fileMenu.add(fileMenuExit);
        fileMenu.setFont(f);        //���ò˵������������
  
          
        //�༭�˵���ʵ��
        editMenu = new JMenu("�༭(E)");
        editMenuCopy = new JMenuItem("����(C) Ctrl+C");
        editMenuCut = new JMenuItem("����(T) Ctrl+X");
        editMenuPaste = new JMenuItem("ճ��(P) Ctrl+v");
        EMHandler EM=new EMHandler();   //����������
        editMenuCopy.addActionListener(EM);//ע�������
        editMenuCut.addActionListener(EM);
        editMenuPaste.addActionListener(EM);
        editMenu.add(editMenuCopy);
        editMenu.add(editMenuCut);
        editMenu.add(editMenuPaste);
        editMenu.setFont(f);
  
  
        //��ʽ�˵���ʵ��
        formatMenu = new JMenu("��ʽ(O)");            //�����˵�
        formatSet = new JMenu("��ʽ����");          //�����˵�
        lineWrap = new JMenuItem("�Զ�����");       //�����˵���
        cancleLineWrap = new JMenuItem("ȡ���Զ�����");
        wrapStyleWord = new JMenuItem("���в�����");
        cancleWrapStyleWord = new JMenuItem("ȡ�����в�����");
        FMHandler FM=new FMHandler();               //�����¼�������
        lineWrap.addActionListener(FM);             //ע������˵���ļ�����
        cancleLineWrap.addActionListener(FM);
        wrapStyleWord.addActionListener(FM);
        cancleWrapStyleWord.addActionListener(FM);
        formatSet.add(lineWrap);
        formatSet.add(cancleLineWrap);
        formatSet.addSeparator();   //��ӷָ���
        formatSet.add(wrapStyleWord);
        formatSet.add(cancleWrapStyleWord);
        formatMenu.add(formatSet);
        formatMenu.setFont(f);
  
  
        //���˵�ȫ����Ӳ˵�����
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(formatMenu);
  
        //��������ʵ��
        //��ť�ֱ�Ϊ�����򿪡��������桱�������ơ��������С�����ճ����
        toolBar =new JToolBar();    //�������߰�
        b1= new JButton(new ImageIcon("img/open.gif"));
        b2= new JButton(new ImageIcon("img/save.gif"));
        b3= new JButton(new ImageIcon("img/copy.gif"));
        b4= new JButton(new ImageIcon("img/cut.gif"));
        b5= new JButton(new ImageIcon("img/paste.gif"));
        TBHandler TB=new TBHandler();       //������ť������
        b1.addActionListener(TB);   b2.addActionListener(TB);
        b3.addActionListener(TB);   b4.addActionListener(TB);
        b5.addActionListener(TB);
        //�Ѱ�ťȫ����ӵ����߰���
        toolBar.add(b1);    toolBar.add(b2);
        toolBar.add(b3);    toolBar.add(b4);
        toolBar.add(b5);
  
  
        //�����˵���ʵ��
        pm =new JPopupMenu();   //���������˵�
        item1 = new JMenuItem("��");
        item2 = new JMenuItem("����");
        item3 = new JMenuItem("����");
        item4 = new JMenuItem("����");
        item5 = new JMenuItem("ճ��");
        JPHandler JP=new JPHandler();
        item1.addActionListener(JP);  //ע��˵��������¼�������
        item2.addActionListener(JP);
        item3.addActionListener(JP);
        item4.addActionListener(JP);
        item5.addActionListener(JP);
        editor.addMouseListener(JP);  //ע���ı���������¼�������
        pm.add(item1);  pm.add(item2);
        pm.add(item3);  pm.add(item4);
        pm.add(item5);
  
  
  
  
  
          
        //�Ѳ˵������������������˵���ӵ��������
        setJMenuBar(mb);        //��ʾ�˵���
        c.add(toolBar,BorderLayout.NORTH);
          
  
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
  
  
    /*�Զ�����ʵ���ļ��˵�����¼�����*/
    private class JMHandler implements ActionListener
    {  
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==fileMenuOpen){ loadFile(); }
            else if(e.getSource()==fileMenuSave){ saveFile(); }
            else{System.exit(0);}
        }
  
    }
  
    public void loadFile()  //���ļ�����
    {
        JFileChooser fc=new JFileChooser();
        int r=fc.showOpenDialog(this);
        if(r==JFileChooser.APPROVE_OPTION)
        {
            File file=fc.getSelectedFile();
            try{ editor.read(new FileReader(file),null);}
            catch(IOException e){}
        }
    }
          
    public void saveFile()  /*�����ļ��ķ���*/
    {
        JFileChooser fc=new JFileChooser();
        int r=fc.showSaveDialog(this);
        if(r==JFileChooser.APPROVE_OPTION)
        {
            File file=fc.getSelectedFile();
            try{ editor.write(new FileWriter(file));}
            catch(IOException e){}
        }
    }
  
      
    //�༭�˵��˵�����¼�����
    private class EMHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==editMenuCopy)     //ʵ�ָ��ƹ���
            {
                editor.copy();
                editor.requestFocus();
            }
            else if(e.getSource()==editMenuCut)//ʵ�ּ��й���
            {
                editor.cut();
                editor.requestFocus();
            }
            else                                //ʵ��ճ������
            {
                editor.paste();
                editor.requestFocus();
            }
        }
    }
    //��ʽ�˵������˵��Ĳ˵�����¼�����
    private class FMHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==lineWrap){ editor.setLineWrap(true); }
            else if(e.getSource()==cancleLineWrap) { editor.setLineWrap(false);}
            else if(e.getSource()==wrapStyleWord) { editor.setWrapStyleWord(true);}
            else{ editor.setWrapStyleWord(false); }
        }
    }
  
  
    //�Զ�����ʵ�ֹ������İ�ť�¼�����
    private class TBHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1){ loadFile(); }        //ʵ�ִ��ļ�����
            else if(e.getSource()==b2) {saveFile();}    //ʵ�ֱ����ļ�����
            else if(e.getSource()==b3)  //�ļ�����
            {
                editor.copy();
                editor.requestFocus();
            }
            else if(e.getSource()==b4) //�ļ�����
            {
                editor.cut();
                editor.requestFocus();
            }
            else                        //�ļ�ճ��
            {
                editor.paste();
                editor.requestFocus();
            }
        }
    }
  
    //�Զ�����ʵ�ֵ���ʽ�˵����¼�����
    private class JPHandler implements ActionListener,MouseListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==item1){ loadFile(); }     //ʵ�ִ��ļ�����
            else if(e.getSource()==item2) {saveFile();} //ʵ�ֱ����ļ�����
            else if(e.getSource()==item3)   //�ļ�����
            {
                editor.copy();
                editor.requestFocus();
            }
            else if(e.getSource()==item4) //�ļ�����
            {
                editor.cut();
                editor.requestFocus();
            }
            else                        //�ļ�ճ��
            {
                editor.paste();
                editor.requestFocus();
            }
        }
  
        public void mouseReleased(MouseEvent e)
        {
            if(e.isPopupTrigger())                  //�ж��Ƿ�������Ҽ�
                pm.show(editor,e.getX(),e.getY());  //��ʾ����ʽ�˵�
        }
  
        public void mouseClicked(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
    }
  
      
  
    public static void main(String []args)
    {
    	Notepad N=new Notepad();
    }
}
