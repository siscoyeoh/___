package yl.ftp;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class MainTest {
	
	public static void main(String[]args) {
		String ftpHost = "192.168.8.115";
		ftpHost = "localhost";
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		
		try {
//			FTPFile[] files = ftpClient.listDirectories("/copyfiles/test/");
			FTPFile[] files = ftpClient.listFiles("/test/");
//			FTPFile[] files = ftpClient.listFiles();
			System.out.println("files:" + files);
			System.out.println("files.length:" + files.length);
			for (FTPFile f : files) {
				System.out.println(f.getName());
				ftpClient.rename("/test/" + f.getName(), "/test/" + f.getName() + "_TEMP");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void makeDir(FTPClient ftpClient) {
		try {
			boolean md = ftpClient.makeDirectory("/test115/");
			System.out.println(md);
			ftpClient.deleteFile("/aaa/bbb/");
			boolean sf = ftpClient.storeFile("/test115/201512.xls", new FileInputStream("C:/201512.xls"));
			System.out.println(sf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
