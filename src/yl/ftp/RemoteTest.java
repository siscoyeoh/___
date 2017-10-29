package yl.ftp;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class RemoteTest {

	public static void main(String[] args) {
		String ftpHost = "";
		String port = "21";
		String username = "";
		String password = "";
		String pathname = "";
		FTPClient client = null;
		try {
			client = FTPUtils.GetFTPClient(ftpHost, port, username, password);
			System.out.println("连接是否可用:" + 
					client.isAvailable() + ", " + new Date());
			FTPFile[] files = client.listFiles(pathname);
			for (int index = 0; index < files.length; index++) {
				System.out.println(files[index].getName());
			}
			System.out.println("文件个数:" + files.length);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (client != null) {
				try {
					client.logout();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					client.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
