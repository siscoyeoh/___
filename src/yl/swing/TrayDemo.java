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
        if(SystemTray.isSupported()){//�ж�ϵͳ�Ƿ�����  
            //����һ������ͼ�����  
            TrayIcon icon =   
            new TrayIcon(Toolkit.getDefaultToolkit().getImage("/images/zzhg.ico"));  
  
            //���������˵�  
            PopupMenu menu = new PopupMenu();  
            //���һ�������˳��İ�ť  
            MenuItem item = new MenuItem("�˳�");  
            item.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {  
                    System.exit(0);  
                }  
            });  
            menu.add(item);  
            //��ӵ����˵�������ͼ��  
            icon.setPopupMenu(menu);  
            SystemTray tray = SystemTray.getSystemTray();//��ȡϵͳ����  
            try {  
                tray.add(icon);  
            } catch (AWTException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }//������ͼ����ӵ�ϵͳ����  
        }  
    }  
}
