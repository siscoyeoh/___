package yl.swing;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrayDemo {
	public static void main(String[] args) {  
		System.out.println(SystemTray.isSupported());
        if(SystemTray.isSupported()){//判断系统是否托盘  
            //创建一个托盘图标对象  
            TrayIcon icon =   
            new TrayIcon(Toolkit.getDefaultToolkit().getImage("/images/zzhg.ico"));  
  
            //创建弹出菜单  
            PopupMenu menu = new PopupMenu();  
            //添加一个用于退出的按钮  
            MenuItem item = new MenuItem("退出");  
            item.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {  
                    System.exit(0);  
                }  
            });  
            menu.add(item);  
            //添加弹出菜单到托盘图标  
            icon.setPopupMenu(menu);  
            SystemTray tray = SystemTray.getSystemTray();//获取系统托盘  
            try {  
                tray.add(icon);  
            } catch (AWTException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }//将托盘图表添加到系统托盘  
        }  
    }  
}
