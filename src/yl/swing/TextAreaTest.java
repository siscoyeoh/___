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
        text.setText( "我们都有一个家，名字叫中国，兄弟姐妹都很多...... ");
        text.setEditable(false);
        text.addMouseListener(new   MouseAdapter()   {
            public   void   mouseEntered(MouseEvent   mouseEvent)   {
                text.setCursor(new   Cursor(Cursor.TEXT_CURSOR));   //鼠标进入Text区后变为文本输入指针
            }
            public   void   mouseExited(MouseEvent   mouseEvent)   {
                text.setCursor(new   Cursor(Cursor.DEFAULT_CURSOR));   //鼠标离开Text区后恢复默认形态
            }
        });
        text.getCaret().addChangeListener(new   ChangeListener()   {
            public   void   stateChanged(ChangeEvent   e)   {
                text.getCaret().setVisible(true);   //使Text区的文本光标显示
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