package yl.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class Tools {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
//		rename2("192.168.8.123", "/test");
		
//		rename();
//		store("localhost", "/copyfiles/");
//		store("192.168.8.123", "/test");

//		String ftpHost = "192.168.8.115";
//		ftpHost = "localhost";
//		String path = "";
		
//		displayCount("localhost", "/copyfiles/");
//		displayCount("192.168.8.115", "/test115");
//		displayCount("192.168.8.123", "/test");
		
//		displayWrong("localhost", "/copyfiles/");
//		displayWrong("192.168.8.115", "/test115");
//		displayWrong("192.168.8.123", "/test");
		
		int c1 = delete("localhost", "/copyfiles/");
		int c2 = delete("192.168.8.115", "/test115");
		int c3 = delete("192.168.8.123", "/test");
		System.out.println("分别删除文件:" + c1 + ", " + c2 + ", " + c3 + "个");
		display("localhost", "/copyfiles/");
		display("192.168.8.115", "/test115");
		display("192.168.8.123", "/test");
		
	}

	public static int delete(String ftpHost, String path) throws IOException {
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		System.out.println("ftpClient.isConnected():" + ftpClient.isConnected());
		ftpClient.deleteFile(path + "/errorlog.log");
		FTPFile[] files = ftpClient.listFiles(path);
		System.out.println(files.length);
		int c = 0;
		for (FTPFile file :files) {
			ftpClient.deleteFile(path + "/" + file.getName());
			System.out.println(++c);
		}
//		ftpClient.storeFile(path + "/123.xls", new FileInputStream("d:/test.xls"));
		return 0;
	}
	
	public static void display(String ftpHost, String path) throws IOException {
		System.out.println("=========================display start:");
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		FTPFile[] files = ftpClient.listFiles(path);
		int c = 0;
		for (FTPFile file :files) {
			System.out.println(file.getName());
		}
		System.out.println("=========================display end.");
	}
	
	public static void displayCount(String ftpHost, String path) throws IOException {
		System.out.println("=========================displayCount start:" + ftpHost);
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		FTPFile[] files = ftpClient.listFiles(path);
		System.out.println(files.length);
		System.out.println("=========================displayCount end.");
	}
	
	public static void displayWrong(String ftpHost, String path) throws IOException {
		System.out.println("=========================displayWrong start:" + ftpHost);
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		FTPFile[] files = ftpClient.listFiles(path);
		int c = 0;
		for (FTPFile file :files) {
			if (file.getName().length() != 20) {
				System.out.println(file.getName());
			}
		}
		System.out.println("=========================displayWrong end.");
	}
	
	public static void rename() throws IOException {
		String ftpHost = "192.168.8.44";
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		String path = "/copyfiles/";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		ftpClient.rename(path + "/123.xls", path + "/4561.xls");
	}
	
	public static void rename2(String ftpHost, String path) throws IOException {
		
		ftpHost = "192.168.8.44";
		path = "/copyfiles/";
		
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		String suffix = "_TEMP"; // server.getTemp_file_suffix(); // 临时文件后缀
//		String path = server.getDestination_path(); // 文件存放路径
		if (StringUtils.isNotEmpty(suffix)) {
			System.out.println("path:" + path);
			FTPFile[] filesRe = ftpClient.listFiles(path == null ? "" : path);
			for (FTPFile fileRe : filesRe) {
				if (!fileRe.isFile()) continue;
				try {
					String fileName = fileRe.getName();
					System.out.println(fileName);
					if (fileName.endsWith(suffix)) {
						System.out.println("需要重命名的:" + fileName);
						String newName = fileName.substring(0, fileName.length() - suffix.length());
						System.out.println("需要重命名为:" + newName);
						boolean res = ftpClient.rename(
								path + "/" + fileName, 
								path + "/" + newName);
						System.out.println("res:" + res);
//						ftpClient.deleteFile((path == null ? "" : path) + "/" + fileName);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void store(String ftpHost, String path) throws FileNotFoundException, IOException {
//		String ftpHost = "192.168.8.44";
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
//		String path = "/copyfiles/";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		File file = new File("C:/Users/Administrator/Desktop/拉萨项目扩展/ftp助手 - 线程版/虚拟机log/201709190936/errorlog.log");
		ftpClient.storeFile(path + "/" + file.getName(), new FileInputStream(file));
	}

}
