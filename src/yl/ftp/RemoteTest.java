package yl.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import yl.__.PD;

public class RemoteTest {

	public static void main(String[] args) {
		String ftpHost = "127.0.0.1";
		String port = "21";
		String username = "ftpuser";
		String password = "ftpuser";
		String pathname = "/";
		
		ftpHost = "192.168.8.115";
		port = "21";
		username = "ftpuser";
		password = "ftpuser";
////		pathname = "/SWC/awos/ERROR_FILES/20171030/";
////		pathname = "/SWC/awos/ERROR_FILES/";
		pathname = "/datas";
		pathname = "/datas/yyyymmdd";
		
		ftpHost = PD.beijing106Host;
		port = "21";
		username = PD.beijing106Username;
		password = PD.beijing106Password;
		pathname = PD.beijing106Path;
		
		displayFiles(ftpHost, port, username, password, pathname);
//		downloadBacklogFiles(ftpHost, port, username, password, pathname);
		
	}

	/** 下载积压文件 */
	private static void downloadBacklogFiles(String ftpHost, String port,
			String username, String password, String pathname) {
//		AWOS201710300158.UKD, 1509300660000, 文件
//		AWOS201710300201.UKD, 1509300660000, 文件
//		AWOS201710300313.UKD, 1509305160000, 文件
//		AWOS201710300314.UKD, 1509305220000, 文件
//		AWOS201710300315.UKD, 1509305220000, 文件
//		AWOS201710300316.UKD, 1509305160000, 文件
//		AWOS201710300350.UKD, 1509307260000, 文件
//		AWOS201710300449.UKD, 1509310920000, 文件
//		AWOS201710300451.UKD, 1509310860000, 文件
//		AWOS201710300453.UKD, 1509311160000, 文件
//		AWOS201710300454.UKD, 1509311160000, 文件
//		AWOS201710300456.UKD, 1509311160000, 文件
		String fileName = "AWOS201710300449.UKD";
//		fileName = "awosError.log";
		FTPClient client = null;
		try {
			client = FTPUtils.GetFTPClient(ftpHost, port, username, password);
			System.out.println("连接是否可用:" + 
					client.isAvailable() + ", " + new Date());
			InputStream in = client.retrieveFileStream(pathname + "/" + fileName);
			OutputStream out = new FileOutputStream(new File("C:/backlogFiles/" + fileName));
			StreamCopy(in, out);
//			System.out.println("下载积压文件个数:" + backlogFiles.size());
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

	private static void displayFiles(String ftpHost, String port,
			String username, String password, String pathname) {
		FTPClient client = null;
		try {
			client = FTPUtils.GetFTPClient(ftpHost, port, username, password);
			System.out.println("连接是否可用:" + 
					client.isAvailable() + ", " + new Date());
			FTPFile[] files = client.listFiles(pathname);
			for (int index = 0; index < files.length; index++) {
				System.out.println(files[index].getName() + ", " + 
						files[index].getTimestamp().getTimeInMillis() + ", " +
						(files[index].isFile() ? "文件" : (files[index].isDirectory() ? "文件夹" : "未知")));
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
	
	private static void StreamCopy(InputStream is, OutputStream os) {
		try {
			// 文件拷贝
			byte flush[] = new byte[1024];
			int len = 0;
			while (0 <= (len = is.read(flush))) {
				os.write(flush, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 关闭流的注意 先打开的后关
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
