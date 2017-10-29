package yl.swing;

import   javax.swing.*;
import   java.awt.*;
import   java.awt.event.*;
import   javax.swing.event.*;

public   class   TextAreaTest   extends   JFrame   {
    JTextArea   text;

    public   TextAreaTest()   {
        super( "JTextArea ");
        this.addWindowListener(new   WindowAdapter()   {
            public   void   windowClosing(WindowEvent   windowEvent)   {
                System.exit(0);
            }
        });
        text   =   new   JTextArea();
        text.setText( "���Ƕ���һ���ң����ֽ��й����ֵܽ��ö��ܶ�...... ");
        text.setEditable(false);
        text.addMouseListener(new   MouseAdapter()   {
            public   void   mouseEntered(MouseEvent   mouseEvent)   {
                text.setCursor(new   Cursor(Cursor.TEXT_CURSOR));   //������Text�����Ϊ�ı�����ָ��
            }
            public   void   mouseExited(MouseEvent   mouseEvent)   {
                text.setCursor(new   Cursor(Cursor.DEFAULT_CURSOR));   //����뿪Text����ָ�Ĭ����̬
            }
        });
        text.getCaret().addChangeListener(new   ChangeListener()   {
            public   void   stateChanged(ChangeEvent   e)   {
                text.getCaret().setVisible(true);   //ʹText�����ı������ʾ
            }
        });
        this.getContentPane().add(text);
        this.setSize(300,   200);
        this.setVisible(true);
    }
    public   static   void   main(String[]   args)   {
        new   Test();
    }
}