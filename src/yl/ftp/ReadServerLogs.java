package yl.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.xml.sax.InputSource;

public class ReadServerLogs {

	public static void main(String[] args) {
		String ftpHost = "127.0.0.1";
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		String encoding = "utf-8";
		FTPClient ftpClient = FTPUtils.GetFTPClient(ftpHost, port, username, password);
		ftpClient.setControlEncoding(encoding);
		System.out.println(ftpClient.isAvailable());
		try {
//			FTPFile folder = ftpClient.mlistFile("/server_logs");
//			System.out.println(folder.isFile());
//			System.out.println(folder.isDirectory());
			// 读取文件列表
//			FTPFile[] logs = ftpClient.listFiles("/server_logs");
//			for (int i = 0; i < logs.length; i++) {
//				FTPFile log = logs[i];
//				System.out.println(log.getName());
//			}
			// 读取一个文件的文本内容
//			FTPFile file = ftpClient.mlistFile("/server_logs/20170929.txt");
			InputStream is = ftpClient.retrieveFileStream("/server_logs/20170928.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ftpClient.disconnect();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
